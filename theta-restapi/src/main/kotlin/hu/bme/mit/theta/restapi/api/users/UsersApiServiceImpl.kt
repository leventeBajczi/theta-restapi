package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.UserDto
import hu.bme.mit.theta.restapi.model.entities.User
import hu.bme.mit.theta.restapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class UsersApiServiceImpl(@Autowired val repository: UserRepository) : UsersApiService {

    override suspend fun usersGet(): List<UserDto> = repository.findAll().map { UserDto(it) }

    override suspend fun usersIdDelete(id: Int): IdObjectDto {
        repository.deleteById(id)
        return IdObjectDto(id)
    }

    override suspend fun usersIdGet(id: Int): UserDto = UserDto(repository.findById(id).orElseThrow {NoSuchElement()})

    override suspend fun usersIdPut(userDto: UserDto): IdObjectDto = IdObjectDto(repository.save(User(userDto)).id)

    override suspend fun usersPost(userDto: UserDto): IdObjectDto = IdObjectDto(repository.save(User(userDto)).id)
}
