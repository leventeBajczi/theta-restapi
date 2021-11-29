package hu.bme.mit.theta.restapi.api.tasks

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class TasksApiServiceImpl(
    @Autowired
    val repository: TaskRepository,
    @Autowired
    val fileRepository: FileRepository
) : TasksApiService {


    override fun tasksGet(): Flow<TaskDto> = flow { repository.findAll(); }

    override suspend fun tasksIdDelete(id: Int): IdObjectDto {
        repository.deleteById(id)
        return IdObjectDto(id)
    }

    override suspend fun tasksIdGet(id: Int): TaskDto = TaskDto(repository.findById(id).orElseThrow(), fileRepository)

    override suspend fun tasksIdInputGet(id: Int): MultiInputDto = repository.findById(id).orElseThrow().readInputs(fileRepository, true)

    override suspend fun tasksPost(task: TaskDto): IdObjectDto = IdObjectDto(Task(task, fileRepository).id)
}