package hu.bme.mit.theta.restapi.api.workers

import hu.bme.mit.theta.restapi.api.handleRESTStatus
import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import org.springframework.beans.factory.annotation.Autowired
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
    suspend fun workersGet(): ResponseEntity<List<WorkerDto>> {
        return handleRESTStatus {service.workersGet()}
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/workers/{id}"],
        produces = ["application/json"]
    )
    suspend fun workersIdDelete( @PathVariable("id") id: Int
): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.workersIdDelete(id)}
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/workers/{id}"],
        produces = ["application/json"]
    )
    suspend fun workersIdGet( @PathVariable("id") id: Int
): ResponseEntity<WorkerDto> {
        return handleRESTStatus {service.workersIdGet(id)}
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/workers/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun workersIdPut( @RequestBody workerDto: WorkerDto): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.workersIdPut(workerDto)}
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/workers"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun workersPost( @RequestBody workerDto: WorkerDto): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.workersIdPut(workerDto)}
    }
}
