package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Resources
import hu.bme.mit.theta.restapi.model.User
import kotlinx.coroutines.flow.Flow;

interface UsersApiService {

    fun usersGet(): Flow<User>

    suspend fun usersIdDelete(id: kotlin.Int): IdObject

    suspend fun usersIdGet(id: kotlin.Int): User

    suspend fun usersIdPut(id: kotlin.Int, id2: kotlin.Int, name: kotlin.String, permissions: kotlin.collections.List<kotlin.String>?, quotas: Resources?): IdObject

    suspend fun usersPost(id: kotlin.Int, name: kotlin.String, permissions: kotlin.collections.List<kotlin.String>?, quotas: Resources?): IdObject
}
