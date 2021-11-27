package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Input
import hu.bme.mit.theta.restapi.model.Task
import hu.bme.mit.theta.restapi.model.TaskBenchmark
import hu.bme.mit.theta.restapi.model.User
import kotlinx.coroutines.flow.Flow;

interface TasksApiService {

    fun tasksGet(): Flow<Task>

    suspend fun tasksIdDelete(id: kotlin.Int): IdObject

    suspend fun tasksIdGet(id: kotlin.Int): Task

    suspend fun tasksIdInputGet(id: kotlin.Int): Input

    suspend fun tasksIdPut(id: kotlin.Int, id2: kotlin.Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: kotlin.collections.List<kotlin.String>?, priority: kotlin.String, benchmark: TaskBenchmark?): IdObject

    suspend fun tasksPost(id: kotlin.Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: kotlin.collections.List<kotlin.String>?, priority: kotlin.String, benchmark: TaskBenchmark?): IdObject
}
