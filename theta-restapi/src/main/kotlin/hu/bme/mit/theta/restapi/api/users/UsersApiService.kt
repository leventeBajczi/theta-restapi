package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.UserDto

interface UsersApiService {

    suspend fun usersGet(): List<UserDto>

    suspend fun usersIdDelete(id: Int): IdObjectDto

    suspend fun usersIdGet(id: Int): UserDto

    suspend fun usersIdPut(userDto: UserDto): IdObjectDto

    suspend fun usersPost(userDto: UserDto): IdObjectDto
}
