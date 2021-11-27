package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.Executable
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
class ExecutablesApiController(@Autowired(required = true) val service: ExecutablesApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/runexec"],
        produces = ["application/json"]
    )
    suspend fun runexecGet(): ResponseEntity<Executable> {
        return ResponseEntity(service.runexecGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/runexec"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun runexecPut( @RequestParam(value="version", required=true) version: String
, @RequestParam(value="description", required=true) description: String
, @RequestParam(value="binary", required=true) binary: String
, @RequestParam(value="commit", required=false) commit: String?
): ResponseEntity<Executable> {
        return ResponseEntity(service.runexecPut(version, description, binary, commit), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/theta"],
        produces = ["application/json"]
    )
    suspend fun thetaGet(): ResponseEntity<Executable> {
        return ResponseEntity(service.thetaGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/theta"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun thetaPut( @RequestParam(value="version", required=true) version: String
, @RequestParam(value="description", required=true) description: String
, @RequestParam(value="binary", required=true) binary: String
, @RequestParam(value="commit", required=false) commit: String?
): ResponseEntity<Executable> {
        return ResponseEntity(service.thetaPut(version, description, binary, commit), HttpStatus.valueOf(200))
    }
}
