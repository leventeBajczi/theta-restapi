package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Resources
import hu.bme.mit.theta.restapi.model.User
import kotlinx.coroutines.flow.Flow;
import org.springframework.stereotype.Service
@Service
class UsersApiServiceImpl : UsersApiService {

    override fun usersGet(): Flow<User> {
        TODO("Implement me")
    }

    override suspend fun usersIdDelete(id: kotlin.Int): IdObject {
        TODO("Implement me")
    }

    override suspend fun usersIdGet(id: kotlin.Int): User {
        TODO("Implement me")
    }

    override suspend fun usersIdPut(id: kotlin.Int, id2: kotlin.Int, name: kotlin.String, permissions: kotlin.collections.List<kotlin.String>?, quotas: Resources?): IdObject {
        TODO("Implement me")
    }

    override suspend fun usersPost(id: kotlin.Int, name: kotlin.String, permissions: kotlin.collections.List<kotlin.String>?, quotas: Resources?): IdObject {
        TODO("Implement me")
    }
}
