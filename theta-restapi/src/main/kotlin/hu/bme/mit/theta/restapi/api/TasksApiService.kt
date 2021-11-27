package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Input
import hu.bme.mit.theta.restapi.model.Task
import hu.bme.mit.theta.restapi.model.TaskBenchmark
import hu.bme.mit.theta.restapi.model.User
import kotlinx.coroutines.flow.Flow;

interface TasksApiService {

    fun tasksGet(): Flow<Task>

    suspend fun tasksIdDelete(id: Int): IdObject

    suspend fun tasksIdGet(id: Int): Task

    suspend fun tasksIdInputGet(id: Int): Input

    suspend fun tasksIdPut(id: Int, id2: Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: List<String>?, priority: String, benchmark: TaskBenchmark?): IdObject

    suspend fun tasksPost(id: Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: List<String>?, priority: String, benchmark: TaskBenchmark?): IdObject
}
