package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.api.handleRESTStatus
import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class UsersApiController(@Autowired(required = true) val service: UsersApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users"],
        produces = ["application/json"]
    )
    suspend fun usersGet(): ResponseEntity<List<UserDto>> {
        return handleRESTStatus {service.usersGet()}
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdDelete( @PathVariable("id") id: Int
): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.usersIdDelete(id)}
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdGet( @PathVariable("id") id: Int
): ResponseEntity<UserDto> {
        return handleRESTStatus {service.usersIdGet(id)}
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/users/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun usersIdPut( @RequestBody userDto: UserDto): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.usersIdPut(userDto)}
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun usersPost( @RequestBody userDto: UserDto): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.usersIdPut(userDto)}
    }
}
