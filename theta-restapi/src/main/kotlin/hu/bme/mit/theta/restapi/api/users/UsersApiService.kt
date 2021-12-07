package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.input.InUserDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutUserDto

interface UsersApiService {

    suspend fun usersGet(): List<OutUserDto>

    suspend fun usersIdDelete(id: Int): IdObjectDto

    suspend fun usersIdGet(id: Int): OutUserDto

    suspend fun usersIdPut(userDto: InUserDto, id: Int = 0): IdObjectDto

    suspend fun usersPost(userDto: InUserDto): IdObjectDto
}
