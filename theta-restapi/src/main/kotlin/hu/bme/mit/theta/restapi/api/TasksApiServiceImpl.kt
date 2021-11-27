package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Input
import hu.bme.mit.theta.restapi.model.Task
import hu.bme.mit.theta.restapi.model.TaskBenchmark
import hu.bme.mit.theta.restapi.model.User
import kotlinx.coroutines.flow.Flow;
import org.springframework.stereotype.Service
@Service
class TasksApiServiceImpl : TasksApiService {

    override fun tasksGet(): Flow<Task> {
        TODO("Implement me")
    }

    override suspend fun tasksIdDelete(id: kotlin.Int): IdObject {
        TODO("Implement me")
    }

    override suspend fun tasksIdGet(id: kotlin.Int): Task {
        TODO("Implement me")
    }

    override suspend fun tasksIdInputGet(id: kotlin.Int): Input {
        TODO("Implement me")
    }

    override suspend fun tasksIdPut(id: kotlin.Int, id2: kotlin.Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: kotlin.collections.List<kotlin.String>?, priority: kotlin.String, benchmark: TaskBenchmark?): IdObject {
        TODO("Implement me")
    }

    override suspend fun tasksPost(id: kotlin.Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: kotlin.collections.List<kotlin.String>?, priority: kotlin.String, benchmark: TaskBenchmark?): IdObject {
        TODO("Implement me")
    }
}
