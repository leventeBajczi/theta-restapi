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

    override suspend fun usersIdDelete(id: Int): IdObject {
        TODO("Implement me")
    }

    override suspend fun usersIdGet(id: Int): User {
        TODO("Implement me")
    }

    override suspend fun usersIdPut(id: Int, id2: Int, name: String, permissions: List<String>?, quotas: Resources?): IdObject {
        TODO("Implement me")
    }

    override suspend fun usersPost(id: Int, name: String, permissions: List<String>?, quotas: Resources?): IdObject {
        TODO("Implement me")
    }
}
