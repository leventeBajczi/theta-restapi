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
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import hu.bme.mit.theta.restapi.utils.iface.ThetaRunner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.apache.http.impl.client.HttpClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory
import java.io.File
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import kotlin.collections.LinkedHashMap
import kotlin.collections.LinkedHashSet


@Component
class TaskSchedulerRunner (
    @Autowired val taskRepository: TaskRepository,
    @Autowired val config: ApplicationConfiguration,
    @Autowired val fileRepository: FileRepository,
    @Autowired val workerRepository: WorkerRepository,
    @Autowired val runexecRunner: RunexecRunner,
    @Autowired val executableUtils: ExecutableUtils
    ) : ThetaRunner{

    private val taskAllocation = LinkedHashMap<Task, WorkerWrapper>()
    private val taskForeignKeyMap = LinkedHashMap<Task, Int>()
    private val workers = LinkedHashMap<WorkerWrapper, Task?>()
    private val workerLookup = LinkedHashMap<Int, WorkerWrapper>()
    private val queue = LinkedBlockingQueue<Task>()
    private var localCounter = 0
    private val executor = Executors.newSingleThreadExecutor()

    override fun runTask(task: Task) {
        println("Received task $task")
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
            for (workerEntry in workerLookup) {
                workerEntry.value.updateBinaries()
            }
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    @Scheduled(fixedDelay = 1000)
    private fun allocateTasks() {
        try {
            LinkedHashMap(taskAllocation).forEach {
                val task = it.key
                val status = it.value.getStatus(task)
                if (status != null) {
                    val tempStdout = File.createTempFile("stdout", ".txt", File(config.tmp))
                    val tempStderr = File.createTempFile("stderr", ".txt", File(config.tmp))
                    tempStdout.writeBytes(Base64.getDecoder().decode(status.stdout ?: ""))
                    tempStderr.writeBytes(Base64.getDecoder().decode(status.stderr ?: ""))
                    taskRepository.save(
                        task.copy(
                            usedTimeS = status.usedTimeS,
                            usedCpuTimeS = status.usedCpuTimeS,
                            usedRamMb = status.usedRamMb,
                            stdout = tempStdout,
                            stderr = tempStderr,
                            retval = status.retval,
                            terminationreason = status.terminationreason
                        )
                    )
                }
            }

            LinkedHashMap(workers).forEach {
                if (it.value == null) {
                    val resources = it.key.getResources()
                    if (resources != null) {
                        val thetaVersions = it.key.getInstalledVersions("theta")
                        val runexecVersions = it.key.getInstalledVersions("runexec")
                        synchronized(queue) {
                            val task: Task? = queue.peek()
                            if (task != null) {
                                if (task.logicalCpu <= (resources.logicalCpu ?: 0) &&
                                    task.ramMb <= (resources.ramM ?: 0) &&
                                    ((task.toolVersion == null && thetaVersions.isNotEmpty()) || thetaVersions.contains(task.toolVersion)) &&
                                    ((task.runexecVersion == null && runexecVersions.isNotEmpty()) || runexecVersions.contains(task.runexecVersion))
                                ) {
                                    val dispatchedTaskId = it.key.dispatchTask(task)
                                    if (dispatchedTaskId != null) {
                                        println("Dispatched to ${it.key.worker} task $task")
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

        init {
            updateBinaries()
        }

        fun updateBinaries() {
            synchronized(this){
                GlobalScope.async {
                    try {
                        val headers = HttpHeaders()
                        headers.contentType = MediaType.MULTIPART_FORM_DATA

                        val installedToolVersions = getInstalledVersions("theta")
                        for (toolVersion in executableUtils.getAllExecutableVersions("theta.zip")) {
                            if (!installedToolVersions.contains(toolVersion.key)) {
                                val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
                                val zipFile = executableUtils.getArchive("theta.zip", toolVersion.key)
                                body.add("binary", FileSystemResource(zipFile))
                                body.add("version", toolVersion.key)
                                body.add("relativePath", executableUtils.getRelativePath("theta.zip", toolVersion.key))
                                val requestEntity = HttpEntity(body, headers)

                                try {
                                    System.err.println("Sending theta to " + worker.name)
                                    restTemplate.put("/theta", requestEntity)
                                    System.err.println("Sent theta to " + worker.name)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                } finally {
                                    zipFile.delete()
                                }
                            }
                        }
                        val installedRunexecVersions = getInstalledVersions("runexec")
                        for (runexecVersion in executableUtils.getAllExecutableVersions("runexec.zip")) {
                            if (!installedRunexecVersions.contains(runexecVersion.key)) {
                                val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
                                val zipFile = executableUtils.getArchive("runexec.zip", runexecVersion.key)
                                body.add(
                                    "binary",
                                    FileSystemResource(zipFile)
                                )
                                body.add("version", runexecVersion.key)
                                body.add(
                                    "relativePath",
                                    executableUtils.getRelativePath("runexec.zip", runexecVersion.key)
                                )
                                val requestEntity = HttpEntity(body, headers)

                                try {
                                    System.err.println("Sending runexec to " + worker.name)
                                    restTemplate.put("/runexec", requestEntity)
                                    System.err.println("Sent runexec to " + worker.name)
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                } finally {
                                    zipFile.delete()
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun getInstalledVersions(s: String) : List<*> {
            try {
                return restTemplate.getForObject("/$s/versions", List::class.java)!!
            } catch(e: Exception) {
                e.printStackTrace()
                throw e
            }
        }

        fun getResources() : OutResourcesDto? {
            var toThrow: Exception = Exception()
            repeat(5) {
                try {
                    return restTemplate.getForObject("/resources", OutResourcesDto::class.java)
                } catch (e: Exception) {
                    toThrow = e
                    Thread.sleep(100)
                }
            }
            toThrow.printStackTrace()
            throw toThrow
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
                val headers = HttpHeaders()
                headers.contentType = MediaType.APPLICATION_JSON
                val entity: HttpEntity<InTaskDto> = HttpEntity(taskDto, headers)
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