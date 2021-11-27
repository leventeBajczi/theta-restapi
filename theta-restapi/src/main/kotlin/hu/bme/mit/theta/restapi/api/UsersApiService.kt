package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.ResourcesDto
import hu.bme.mit.theta.restapi.model.dtos.UserDto
import kotlinx.coroutines.flow.Flow;

interface UsersApiService {

    fun usersGet(): Flow<UserDto>

    suspend fun usersIdDelete(id: Int): IdObjectDto

    suspend fun usersIdGet(id: Int): UserDto

    suspend fun usersIdPut(id: Int, id2: Int, name: String, permissions: List<String>?, quotas: ResourcesDto?): IdObjectDto

    suspend fun usersPost(id: Int, name: String, permissions: List<String>?, quotas: ResourcesDto?): IdObjectDto
}
