package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class WorkersApiTest {

    private val service: WorkersApiService = WorkersApiServiceImpl()
    private val api: WorkersApiController = WorkersApiController(service)

    /**
     * To test WorkersApiController.workersGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun workersGetTest() = runBlockingTest {
        val response: ResponseEntity<Flow<WorkerDto>> = api.workersGet()

        // TODO: test validations
    }

    /**
     * To test WorkersApiController.workersIdDelete
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun workersIdDeleteTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val response: ResponseEntity<IdObject> = api.workersIdDelete(id)

        // TODO: test validations
    }

    /**
     * To test WorkersApiController.workersIdGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun workersIdGetTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val response: ResponseEntity<Worker> = api.workersIdGet(id)

        // TODO: test validations
    }

    /**
     * To test WorkersApiController.workersIdPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun workersIdPutTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val id2:kotlin.Int? = null
//        val address:kotlin.String? = null
//        val name:kotlin.String? = null
//        val response: ResponseEntity<IdObject> = api.workersIdPut(id, id2, address, name)

        // TODO: test validations
    }

    /**
     * To test WorkersApiController.workersPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun workersPostTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val address:kotlin.String? = null
//        val name:kotlin.String? = null
//        val response: ResponseEntity<IdObject> = api.workersPost(id, address, name)

        // TODO: test validations
    }

}
