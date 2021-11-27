package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.*
import kotlinx.coroutines.flow.Flow

interface TasksApiService {

    fun tasksGet(): Flow<TaskDto>

    suspend fun tasksIdDelete(id: Int): IdObjectDto

    suspend fun tasksIdGet(id: Int): TaskDto

    suspend fun tasksIdInputGet(id: Int): InputDto

    suspend fun tasksIdPut(id: Int, id2: Int, timestamp: java.time.OffsetDateTime, input: InputDto, user: UserDto, parameters: List<String>?, priority: String, benchmark: TaskBenchmarkDto?): IdObjectDto

    suspend fun tasksPost(id: Int, timestamp: java.time.OffsetDateTime, input: InputDto, user: UserDto, parameters: List<String>?, priority: String, benchmark: TaskBenchmarkDto?): IdObjectDto
}
