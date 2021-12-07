package hu.bme.mit.theta.restapi.api.executables

import hu.bme.mit.theta.restapi.api.handleRESTStatus
import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutExecutableDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class ExecutablesApiController(@Autowired(required = true) val service: ExecutablesApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/runexec"],
        produces = ["application/json"]
    )
    suspend fun runexecGet(): ResponseEntity<OutExecutableDto> {
        return handleRESTStatus {service.runexecGet()}
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/runexec"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun runexecPut(
        @RequestParam(name = "binary", required = true) binary: MultipartFile,
        @RequestParam(name = "version", required = true) version: String,
        @RequestParam(name = "commit", required = false) commit: String?,
        @RequestParam("relativePath", required = true) relativePath: String,
        @RequestParam(name = "description", required = true) description: String,
    ): ResponseEntity<OutExecutableDto> {
        return handleRESTStatus {service.runexecPut(
            InExecutableDto(version, description, binaryBytes = binary.bytes, commit = commit, relativePath = relativePath)
        )}
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/theta"],
        produces = ["application/json"]
    )
    suspend fun thetaGet(): ResponseEntity<OutExecutableDto> {
        return handleRESTStatus {service.thetaGet()}
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/theta"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun thetaPut(
        @RequestParam("binary", required=true) binary: MultipartFile,
        @RequestParam("version", required = true) version: String,
        @RequestParam("commit", required = false) commit: String?,
        @RequestParam("relativePath", required = true) relativePath: String,
        @RequestParam("description", required = true) description: String,
    ): ResponseEntity<OutExecutableDto> {
        return handleRESTStatus {
            service.thetaPut(
                InExecutableDto(version = version, description = description, binaryBytes = binary.bytes, commit = commit, relativePath = relativePath)
            )
        }
    }
}
