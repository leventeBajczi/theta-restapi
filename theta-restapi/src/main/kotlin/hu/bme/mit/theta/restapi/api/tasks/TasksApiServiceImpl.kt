package hu.bme.mit.theta.restapi.api.tasks

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.input.InTaskDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutTaskDto
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import hu.bme.mit.theta.restapi.utils.impl.DirectThetaRunner
import hu.bme.mit.theta.restapi.utils.impl.RunexecRunner
import hu.bme.mit.theta.restapi.utils.impl.TaskSchedulerRunner
import hu.bme.mit.theta.restapi.utils.impl.ThetaRunnerAllocator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.Executors

@Service
class TasksApiServiceImpl(
    @Autowired
    val repository: TaskRepository,
    @Autowired
    val fileRepository: FileRepository,
    @Autowired
    val thetaRunner: ThetaRunnerAllocator,
    @Autowired
    val config: ApplicationConfiguration
) : TasksApiService {

    override suspend fun tasksGet(): List<OutTaskDto> = repository.findAll().map { OutTaskDto(it, fileRepository) }

    override suspend fun tasksIdDelete(id: Int): IdObjectDto {
        if(repository.existsById(id)) {
            repository.deleteById(id)
        }
        return IdObjectDto(id)
    }

    override suspend fun tasksIdGet(id: Int): OutTaskDto = OutTaskDto(repository.findById(id).orElseThrow { NoSuchElement() }, fileRepository)

    override suspend fun tasksIdInputGet(id: Int): MultiInputDto = repository.findById(id).orElseThrow { NoSuchElement() }.readInputs(fileRepository, true)

    override suspend fun tasksPost(task: InTaskDto): IdObjectDto {
        val savedTask = repository.save(Task(task, fileRepository, config))
        thetaRunner.runTask(savedTask)
        return IdObjectDto(savedTask.id)
    }
}
