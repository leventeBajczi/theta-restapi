package hu.bme.mit.theta.restapi.api.tasks

import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import hu.bme.mit.theta.restapi.utils.iface.ThetaRunner
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
    val thetaRunner: ThetaRunner,
) : TasksApiService {

    val executorService = Executors.newFixedThreadPool(1)


    override suspend fun tasksGet(): List<TaskDto> = repository.findAll().map { TaskDto(it, fileRepository) }

    override suspend fun tasksIdDelete(id: Int): IdObjectDto {
        if(repository.existsById(id)) {
            repository.deleteById(id)
        }
        return IdObjectDto(id)
    }

    override suspend fun tasksIdGet(id: Int): TaskDto = TaskDto(repository.findById(id).orElseThrow { NoSuchElement() }, fileRepository)

    override suspend fun tasksIdInputGet(id: Int): MultiInputDto = repository.findById(id).orElseThrow { NoSuchElement() }.readInputs(fileRepository, true)

    override suspend fun tasksPost(task: TaskDto): IdObjectDto {
        val savedTask = repository.save(Task(task, fileRepository))
        executorService.submit { thetaRunner.runTask(savedTask) }
        return IdObjectDto(savedTask.id)
    }
}
