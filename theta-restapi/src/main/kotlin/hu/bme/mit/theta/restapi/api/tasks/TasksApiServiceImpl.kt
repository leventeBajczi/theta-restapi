package hu.bme.mit.theta.restapi.api.tasks

import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class TasksApiServiceImpl(
    @Autowired
    val repository: TaskRepository,
    @Autowired
    val fileRepository: FileRepository
) : TasksApiService {


    override suspend fun tasksGet(): List<TaskDto> = repository.findAll().map { TaskDto(it, fileRepository) }

    override suspend fun tasksIdDelete(id: Int): IdObjectDto {
        if(repository.existsById(id)) {
            repository.deleteById(id)
        }
        return IdObjectDto(id)
    }

    override suspend fun tasksIdGet(id: Int): TaskDto = TaskDto(repository.findById(id).orElseThrow { NoSuchElement }, fileRepository)

    override suspend fun tasksIdInputGet(id: Int): MultiInputDto = repository.findById(id).orElseThrow { NoSuchElement }.readInputs(fileRepository, true)

    override suspend fun tasksPost(task: TaskDto): IdObjectDto {
        val savedTask = repository.save(Task(task, fileRepository))
        return IdObjectDto(savedTask.id)
    }
}
