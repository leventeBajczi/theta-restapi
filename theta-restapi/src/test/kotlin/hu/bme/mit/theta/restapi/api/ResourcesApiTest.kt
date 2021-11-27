package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
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
        val response: ResponseEntity<StaticResourcesDto> = api.resourcesGet()

        // TODO: test validations
    }

}
