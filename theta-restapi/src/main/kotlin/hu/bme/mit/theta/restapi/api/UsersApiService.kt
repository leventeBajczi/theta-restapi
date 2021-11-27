package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Resources
import hu.bme.mit.theta.restapi.model.User
import kotlinx.coroutines.flow.Flow;

interface UsersApiService {

    fun usersGet(): Flow<User>

    suspend fun usersIdDelete(id: Int): IdObject

    suspend fun usersIdGet(id: Int): User

    suspend fun usersIdPut(id: Int, id2: Int, name: String, permissions: List<String>?, quotas: Resources?): IdObject

    suspend fun usersPost(id: Int, name: String, permissions: List<String>?, quotas: Resources?): IdObject
}
