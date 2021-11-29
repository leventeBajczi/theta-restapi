package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.UserDto
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service
@Service
class UsersApiServiceImpl : UsersApiService {

    override fun usersGet(): Flow<UserDto> {
        TODO("Implement me")
    }

    override suspend fun usersIdDelete(id: Int): IdObjectDto {
        TODO("Implement me")
    }

    override suspend fun usersIdGet(id: Int): UserDto {
        TODO("Implement me")
    }

    override suspend fun usersIdPut(userDto: UserDto): IdObjectDto {
        TODO("Implement me")
    }

    override suspend fun usersPost(userDto: UserDto): IdObjectDto {
        TODO("Implement me")
    }
}
