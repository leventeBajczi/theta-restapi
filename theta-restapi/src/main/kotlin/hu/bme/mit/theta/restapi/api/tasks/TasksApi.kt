package hu.bme.mit.theta.restapi.api.tasks


import hu.bme.mit.theta.restapi.api.handleRESTStatus
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.input.InTaskDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutTaskDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class TasksApiController(@Autowired(required = true) val service: TasksApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks"],
        produces = ["application/json"]
    )
    suspend fun tasksGet(): ResponseEntity<List<OutTaskDto>> {
        return handleRESTStatus {service.tasksGet()}
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/tasks/{id}"],
        produces = ["application/json"]
    )
    suspend fun tasksIdDelete( @PathVariable("id") id: Int
    ): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.tasksIdDelete(id)}
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks/{id}"],
        produces = ["application/json"]
    )
    suspend fun tasksIdGet( @PathVariable("id") id: Int
    ): ResponseEntity<OutTaskDto> {
        return handleRESTStatus {service.tasksIdGet(id)}
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks/{id}/input"],
        produces = ["multipart/form-data"]
    )
    suspend fun tasksIdInputGet( @PathVariable("id") id: Int
    ): ResponseEntity<MultiInputDto> {
        return handleRESTStatus {service.tasksIdInputGet(id)}
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/tasks"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun tasksPost( @RequestBody taskDto: InTaskDto
    ): ResponseEntity<IdObjectDto> {
        return handleRESTStatus {service.tasksPost(taskDto)}
    }
}
