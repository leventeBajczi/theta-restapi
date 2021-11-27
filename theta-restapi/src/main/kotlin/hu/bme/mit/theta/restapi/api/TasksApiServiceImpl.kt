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

    override suspend fun tasksIdDelete(id: Int): IdObject {
        TODO("Implement me")
    }

    override suspend fun tasksIdGet(id: Int): Task {
        TODO("Implement me")
    }

    override suspend fun tasksIdInputGet(id: Int): Input {
        TODO("Implement me")
    }

    override suspend fun tasksIdPut(id: Int, id2: Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: List<String>?, priority: String, benchmark: TaskBenchmark?): IdObject {
        TODO("Implement me")
    }

    override suspend fun tasksPost(id: Int, timestamp: java.time.OffsetDateTime, input: Input, user: User, parameters: List<String>?, priority: String, benchmark: TaskBenchmark?): IdObject {
        TODO("Implement me")
    }
}
