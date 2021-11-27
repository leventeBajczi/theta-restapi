package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.Executable
import org.junit.jupiter.api.Test
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.test.runBlockingTest
import org.springframework.http.ResponseEntity

class ExecutablesApiTest {

    private val service: ExecutablesApiService = ExecutablesApiServiceImpl()
    private val api: ExecutablesApiController = ExecutablesApiController(service)

    /**
     * To test ExecutablesApiController.runexecGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun runexecGetTest() = runBlockingTest {
        val response: ResponseEntity<Executable> = api.runexecGet()

        // TODO: test validations
    }

    /**
     * To test ExecutablesApiController.runexecPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun runexecPutTest() = runBlockingTest {
        val version:kotlin.String? = null
        val description:kotlin.String? = null
        val binary:kotlin.String? = null
        val commit:kotlin.String? = null
        val response: ResponseEntity<Executable> = api.runexecPut(version, description, binary, commit)

        // TODO: test validations
    }

    /**
     * To test ExecutablesApiController.thetaGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun thetaGetTest() = runBlockingTest {
        val response: ResponseEntity<Executable> = api.thetaGet()

        // TODO: test validations
    }

    /**
     * To test ExecutablesApiController.thetaPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun thetaPutTest() = runBlockingTest {
        val version:kotlin.String? = null
        val description:kotlin.String? = null
        val binary:kotlin.String? = null
        val commit:kotlin.String? = null
        val response: ResponseEntity<Executable> = api.thetaPut(version, description, binary, commit)

        // TODO: test validations
    }

}
