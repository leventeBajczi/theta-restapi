package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class ExecutablesApiController(@Autowired(required = true) val service: ExecutablesApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/runexec"],
        produces = ["application/json"]
    )
    suspend fun runexecGet(): ResponseEntity<ExecutableDto> {
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
): ResponseEntity<ExecutableDto> {
        return ResponseEntity(service.runexecPut(version, description, binary, commit), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/theta"],
        produces = ["application/json"]
    )
    suspend fun thetaGet(): ResponseEntity<ExecutableDto> {
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
): ResponseEntity<ExecutableDto> {
        return ResponseEntity(service.thetaPut(version, description, binary, commit), HttpStatus.valueOf(200))
    }
}
