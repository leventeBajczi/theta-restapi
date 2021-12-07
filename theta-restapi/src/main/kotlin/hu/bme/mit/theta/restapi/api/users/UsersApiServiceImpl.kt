package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.input.InUserDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutUserDto
import hu.bme.mit.theta.restapi.model.entities.User
import hu.bme.mit.theta.restapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
@Service
class UsersApiServiceImpl(@Autowired val repository: UserRepository) : UsersApiService {

    override suspend fun usersGet(): List<OutUserDto> = repository.findAll().map { OutUserDto(it) }

    override suspend fun usersIdDelete(id: Int): IdObjectDto {
        repository.deleteById(id)
        return IdObjectDto(id)
    }

    override suspend fun usersIdGet(id: Int): OutUserDto = OutUserDto(repository.findById(id).orElseThrow {NoSuchElement()})

    override suspend fun usersIdPut(userDto: InUserDto, id: Int): IdObjectDto = IdObjectDto(repository.save(User(userDto, id)).id)

    override suspend fun usersPost(userDto: InUserDto): IdObjectDto = IdObjectDto(repository.save(User(userDto)).id)
}
