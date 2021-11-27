package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class WorkersApiController(@Autowired(required = true) val service: WorkersApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/workers"],
        produces = ["application/json"]
    )
    fun workersGet(): ResponseEntity<Flow<WorkerDto>> {
        return ResponseEntity(service.workersGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/workers/{id}"],
        produces = ["application/json"]
    )
    suspend fun workersIdDelete( @PathVariable("id") id: Int
): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.workersIdDelete(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/workers/{id}"],
        produces = ["application/json"]
    )
    suspend fun workersIdGet( @PathVariable("id") id: Int
): ResponseEntity<WorkerDto> {
        return ResponseEntity(service.workersIdGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/workers/{id}"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun workersIdPut( @PathVariable("id") id: Int
, @RequestParam(value="id", required=true) id2: Int
, @RequestParam(value="address", required=true) address: String
, @RequestParam(value="name", required=false) name: String?
): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.workersIdPut(id, id2, address, name), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/workers"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun workersPost( @RequestParam(value="id", required=true) id: Int
, @RequestParam(value="address", required=true) address: String
, @RequestParam(value="name", required=false) name: String?
): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.workersPost(id, address, name), HttpStatus.valueOf(200))
    }
}
