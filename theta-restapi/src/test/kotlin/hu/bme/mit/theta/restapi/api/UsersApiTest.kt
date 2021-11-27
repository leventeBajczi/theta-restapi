package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.UserDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class UsersApiTest {

    private val service: UsersApiService = UsersApiServiceImpl()
    private val api: UsersApiController = UsersApiController(service)

    /**
     * To test UsersApiController.usersGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun usersGetTest() = runBlockingTest {
        val response: ResponseEntity<Flow<UserDto>> = api.usersGet()

        // TODO: test validations
    }

    /**
     * To test UsersApiController.usersIdDelete
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun usersIdDeleteTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val response: ResponseEntity<IdObject> = api.usersIdDelete(id)

        // TODO: test validations
    }

    /**
     * To test UsersApiController.usersIdGet
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun usersIdGetTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val response: ResponseEntity<User> = api.usersIdGet(id)

        // TODO: test validations
    }

    /**
     * To test UsersApiController.usersIdPut
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun usersIdPutTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val id2:kotlin.Int? = null
//        val name:kotlin.String? = null
//        val permissions:kotlin.collections.List<kotlin.String>? = null
//        val quotas:Resources? = null
//        val response: ResponseEntity<IdObject> = api.usersIdPut(id, id2, name, permissions, quotas)

        // TODO: test validations
    }

    /**
     * To test UsersApiController.usersPost
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    fun usersPostTest() = runBlockingTest {
//        val id:kotlin.Int? = null
//        val name:kotlin.String? = null
//        val permissions:kotlin.collections.List<kotlin.String>? = null
//        val quotas:Resources? = null
//        val response: ResponseEntity<IdObject> = api.usersPost(id, name, permissions, quotas)

        // TODO: test validations
    }

}
