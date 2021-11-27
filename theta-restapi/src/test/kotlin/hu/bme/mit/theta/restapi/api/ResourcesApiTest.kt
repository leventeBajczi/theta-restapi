package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.StaticResources
import org.junit.jupiter.api.Test
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.test.runBlockingTest
import org.springframework.http.ResponseEntity

class ResourcesApiTest {

    private val service: ResourcesApiService = ResourcesApiServiceImpl()
    private val api: ResourcesApiController = ResourcesApiController(service)

    /**
     * To test ResourcesApiController.resourcesGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun resourcesGetTest() = runBlockingTest {
        val response: ResponseEntity<StaticResources> = api.resourcesGet()

        // TODO: test validations
    }

}
