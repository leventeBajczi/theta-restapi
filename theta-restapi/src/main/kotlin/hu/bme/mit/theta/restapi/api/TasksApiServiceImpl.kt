package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.*
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
@Service
class TasksApiServiceImpl : TasksApiService {

    override fun tasksGet(): Flow<TaskDto> {
        TODO("Implement me")
    }

    override suspend fun tasksIdDelete(id: Int): IdObjectDto {
        TODO("Implement me")
    }

    override suspend fun tasksIdGet(id: Int): TaskDto {
        TODO("Implement me")
    }

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
