package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.api.handleRESTStatus
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.input.InUserDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutUserDto
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
    suspend fun usersGet(): ResponseEntity<*> {
        return handleRESTStatus {service.usersGet()}
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdDelete( @PathVariable("id") id: Int
): ResponseEntity<*> {
        return handleRESTStatus {service.usersIdDelete(id)}
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdGet( @PathVariable("id") id: Int
): ResponseEntity<*> {
        return handleRESTStatus {service.usersIdGet(id)}
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/users/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun usersIdPut( @PathVariable id: Int, @RequestBody userDto: InUserDto): ResponseEntity<*> {
        return handleRESTStatus {service.usersIdPut(userDto, id)}
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun usersPost( @RequestBody userDto: InUserDto): ResponseEntity<*> {
        return handleRESTStatus {service.usersIdPut(userDto)}
    }
}
