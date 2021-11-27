package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Input
import hu.bme.mit.theta.restapi.model.Task
import hu.bme.mit.theta.restapi.model.TaskBenchmark
import hu.bme.mit.theta.restapi.model.User
import org.junit.jupiter.api.Test
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.test.runBlockingTest
import org.springframework.http.ResponseEntity

class TasksApiTest {

    private val service: TasksApiService = TasksApiServiceImpl()
    private val api: TasksApiController = TasksApiController(service)

    /**
     * To test TasksApiController.tasksGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun tasksGetTest() = runBlockingTest {
        val response: ResponseEntity<Flow<Task>> = api.tasksGet()

        // TODO: test validations
    }

    /**
     * To test TasksApiController.tasksIdDelete
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun tasksIdDeleteTest() = runBlockingTest {
        val id:kotlin.Int? = null
        val response: ResponseEntity<IdObject> = api.tasksIdDelete(id)

        // TODO: test validations
    }

    /**
     * To test TasksApiController.tasksIdGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun tasksIdGetTest() = runBlockingTest {
        val id:kotlin.Int? = null
        val response: ResponseEntity<Task> = api.tasksIdGet(id)

        // TODO: test validations
    }

    /**
     * To test TasksApiController.tasksIdInputGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun tasksIdInputGetTest() = runBlockingTest {
        val id:kotlin.Int? = null
        val response: ResponseEntity<Input> = api.tasksIdInputGet(id)

        // TODO: test validations
    }

    /**
     * To test TasksApiController.tasksIdPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun tasksIdPutTest() = runBlockingTest {
        val id:kotlin.Int? = null
        val id2:kotlin.Int? = null
        val timestamp:java.time.OffsetDateTime? = null
        val input:Input? = null
        val user:User? = null
        val parameters:kotlin.collections.List<kotlin.String>? = null
        val priority:kotlin.String? = null
        val benchmark:TaskBenchmark? = null
        val response: ResponseEntity<IdObject> = api.tasksIdPut(id, id2, timestamp, input, user, parameters, priority, benchmark)

        // TODO: test validations
    }

    /**
     * To test TasksApiController.tasksPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun tasksPostTest() = runBlockingTest {
        val id:kotlin.Int? = null
        val timestamp:java.time.OffsetDateTime? = null
        val input:Input? = null
        val user:User? = null
        val parameters:kotlin.collections.List<kotlin.String>? = null
        val priority:kotlin.String? = null
        val benchmark:TaskBenchmark? = null
        val response: ResponseEntity<IdObject> = api.tasksPost(id, timestamp, input, user, parameters, priority, benchmark)

        // TODO: test validations
    }

}
