package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.*
import hu.bme.mit.theta.restapi.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class TasksApiServiceImpl(
    @Autowired
    val repository: TaskRepository
) : TasksApiService {


    override fun tasksGet(): Flow<TaskDto> = flow { repository.findAll(); }

    override suspend fun tasksIdDelete(id: Int): IdObjectDto {
        repository.deleteById(id)
        return IdObjectDto(id)
    }

    override suspend fun tasksIdGet(id: Int): TaskDto = TaskDto(repository.findById(id).orElseThrow())

    override suspend fun tasksIdInputGet(id: Int): InputDto {
        TODO("Implement me")
    }

    override suspend fun tasksIdPut(id: Int, id2: Int, timestamp: java.time.OffsetDateTime, input: InputDto, user: UserDto, parameters: List<String>?, priority: String, benchmark: TaskBenchmarkDto?): IdObjectDto {
        TODO("Implement me")
    }

    override suspend fun tasksPost(id: Int, timestamp: java.time.OffsetDateTime, input: InputDto, user: UserDto, parameters: List<String>?, priority: String, benchmark: TaskBenchmarkDto?): IdObjectDto {
        TODO("Implement me")
    }
}
