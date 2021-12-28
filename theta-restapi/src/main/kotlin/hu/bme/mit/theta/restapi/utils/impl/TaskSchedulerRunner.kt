package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.input.InTaskDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutResourcesDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutStatusDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutTaskDto
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.model.entities.Worker
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import hu.bme.mit.theta.restapi.repository.WorkerRepository
import hu.bme.mit.theta.restapi.utils.iface.ThetaRunner
import org.apache.http.impl.client.HttpClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureOrder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue


@Component
class TaskSchedulerRunner (
    @Autowired val taskRepository: TaskRepository,
    @Autowired val config: ApplicationConfiguration,
    @Autowired val fileRepository: FileRepository,
    @Autowired val workerRepository: WorkerRepository,
    @Autowired val runexecRunner: RunexecRunner
    ) : ThetaRunner{

    private val taskAllocation = LinkedHashMap<Task, WorkerWrapper>()
    private val taskForeignKeyMap = LinkedHashMap<Task, Int>()
    private val workers = LinkedHashMap<WorkerWrapper, Task?>()
    private val workerLookup = LinkedHashMap<Int, WorkerWrapper>()
    private val queue = LinkedBlockingQueue<Task>()
    private var localCounter = 0
    private val executor = Executors.newSingleThreadExecutor()

    override fun runTask(task: Task) {
        queue.add(task) 
    }

    @Scheduled(fixedDelay = 30_000)
    fun discoverWorkers() {
        try {
            val allWorkers = workerRepository.findAll()
            val keys = LinkedHashSet(workerLookup.keys)
            allWorkers.forEach {
                keys.remove(it.id)
                if (!workerLookup.containsKey(it.id)) {
                    val workerWrapper = WorkerWrapper(it)
                    workers[workerWrapper] = null
                    workerLookup[it.id] = workerWrapper
                } else if (workerLookup[it.id]?.worker != it) {
                    workerLookup[it.id]?.worker = it
                }
            }
            keys.forEach {
                val workerWrapper = workerLookup[it]
                workers.remove(workerWrapper)
                workerLookup.remove(it)
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    @Scheduled(fixedDelay = 1000)
    private fun allocateTasks() {
        println("Queue size: ${queue.size}")
        try {
            LinkedHashMap(taskAllocation).forEach {
                val task = it.key
                val status = it.value.getStatus(task)
                if (status != null) {
                    val tempStdout = File.createTempFile("stdout", ".txt", File(config.tmp))
                    val tempStderr = File.createTempFile("stderr", ".txt", File(config.tmp))
                    tempStdout.writeText(status.stdout ?: "")
                    tempStderr.writeText(status.stderr ?: "")
                    taskRepository.save(
                        task.copy(
                            usedTimeS = status.usedTimeS,
                            usedCpuTimeS = status.usedCpuTimeS,
                            usedRamMb = status.usedRamMb,
                            stdout = tempStdout,
                            stderr = tempStderr
                        )
                    )
                }
            }

            LinkedHashMap(workers).forEach {
                if (it.value == null) {
                    val resources = it.key.getResources()
                    if (resources != null) {
                        synchronized(queue) {
                            val task: Task? = queue.peek()
                            if (task != null) {
                                if (task.logicalCpu <= resources.logicalCpu ?: 0 &&
                                    task.ramMb <= resources.ramM ?: 0
                                ) {
                                    val dispatchedTaskId = it.key.dispatchTask(task)
                                    if (dispatchedTaskId != null) {
                                        workers[it.key] = task
                                        taskAllocation[task] = it.key
                                        taskForeignKeyMap[task] = dispatchedTaskId
                                        queue.remove(task)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            synchronized(queue) {
                if(queue.isNotEmpty() && localCounter == 0) {
                    localCounter++
                    val task = queue.take()
                    executor.submit {
                        runexecRunner.runTask(task)
                        localCounter--
                    }
                }
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }

    }


    private inner class WorkerWrapper(var worker: Worker) {
        private val restTemplate: RestTemplate = restTemplate(worker.address)

        fun getResources() : OutResourcesDto? {
            try {
                return restTemplate.getForObject("/resources", OutResourcesDto::class.java)
            } catch(e: Exception) {
                e.printStackTrace()
                throw e
            }
        }

        fun getStatus(task: Task) : OutStatusDto? {
            try {
                val status: OutTaskDto? =
                    restTemplate.getForObject("/tasks/" + taskForeignKeyMap[task], OutTaskDto::class.java)
                if (status != null) {
                    return if (status.doneStatus?.stdout != null) {
                        restTemplate.delete("/tasks/" + taskForeignKeyMap[task])
                        taskForeignKeyMap.remove(task)
                        taskAllocation.remove(task)
                        workers[this] = null
                        status.doneStatus
                    } else null
                }
                taskForeignKeyMap.remove(task)
                taskAllocation.remove(task)
                workers[this] = null
                queue.add(task)
                return null
            } catch(e: Exception) {
                e.printStackTrace()
                return null
            }
        }

        fun dispatchTask(task: Task) : Int? {
            return try {
                val taskDto = InTaskDto(task, fileRepository)
                val toSend = taskDto.copy(benchmark = taskDto.benchmark?.copy(useScheduling = false))
                val headers = HttpHeaders()
                headers.contentType = MediaType.APPLICATION_JSON
                val entity: HttpEntity<InTaskDto> = HttpEntity(toSend, headers)
                restTemplate.postForObject("/tasks", entity, IdObjectDto::class.java)?.id
            } catch(e: Exception){
                e.printStackTrace()
                null
            }
        }

        fun restTemplate(apiHost: String): RestTemplate {
            val restTemplate = RestTemplate(clientHttpRequestFactory())
            restTemplate.uriTemplateHandler = DefaultUriBuilderFactory(apiHost)
            return restTemplate
        }

        fun clientHttpRequestFactory(): HttpComponentsClientHttpRequestFactory {
            val clientHttpRequestFactory = HttpComponentsClientHttpRequestFactory()
            clientHttpRequestFactory.httpClient = HttpClients.createDefault()
            return clientHttpRequestFactory
        }
    }
}