package hu.bme.mit.theta.restapi.api.users

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
        return ResponseEntity(service.usersGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdDelete( @PathVariable("id") id: Int
): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.usersIdDelete(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdGet( @PathVariable("id") id: Int
): ResponseEntity<UserDto> {
        return ResponseEntity(service.usersIdGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/users/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun usersIdPut( @RequestBody userDto: UserDto): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.usersIdPut(userDto), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/users"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun usersPost( @RequestBody userDto: UserDto): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.usersIdPut(userDto), HttpStatus.valueOf(200))
    }
}
