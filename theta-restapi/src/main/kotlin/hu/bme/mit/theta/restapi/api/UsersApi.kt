package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Resources
import hu.bme.mit.theta.restapi.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

import org.springframework.web.bind.annotation.*
import org.springframework.validation.annotation.Validated
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.beans.factory.annotation.Autowired

import javax.validation.Valid
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

import kotlinx.coroutines.flow.Flow;
import kotlin.collections.List
import kotlin.collections.Map

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class UsersApiController(@Autowired(required = true) val service: UsersApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users"],
        produces = ["application/json"]
    )
    fun usersGet(): ResponseEntity<Flow<User>> {
        return ResponseEntity(service.usersGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdDelete( @PathVariable("id") id: kotlin.Int
): ResponseEntity<IdObject> {
        return ResponseEntity(service.usersIdDelete(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/users/{id}"],
        produces = ["application/json"]
    )
    suspend fun usersIdGet( @PathVariable("id") id: kotlin.Int
): ResponseEntity<User> {
        return ResponseEntity(service.usersIdGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/users/{id}"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun usersIdPut( @PathVariable("id") id: kotlin.Int
, @RequestParam(value="id", required=true) id2: kotlin.Int 
, @RequestParam(value="name", required=true) name: kotlin.String 
, @RequestParam(value="permissions", required=false) permissions: kotlin.collections.List<kotlin.String>? 
, @RequestParam(value="quotas", required=false) quotas: Resources? 
): ResponseEntity<IdObject> {
        return ResponseEntity(service.usersIdPut(id, id2, name, permissions, quotas), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/users"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun usersPost( @RequestParam(value="id", required=true) id: kotlin.Int 
, @RequestParam(value="name", required=true) name: kotlin.String 
, @RequestParam(value="permissions", required=false) permissions: kotlin.collections.List<kotlin.String>? 
, @RequestParam(value="quotas", required=false) quotas: Resources? 
): ResponseEntity<IdObject> {
        return ResponseEntity(service.usersPost(id, name, permissions, quotas), HttpStatus.valueOf(200))
    }
}
