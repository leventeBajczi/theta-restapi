package hu.bme.mit.theta.restapi.api


import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import kotlinx.coroutines.flow.Flow
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
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
    fun tasksGet(): ResponseEntity<Flow<TaskDto>> {
        return ResponseEntity(service.tasksGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/tasks/{id}"],
        produces = ["application/json"]
    )
    suspend fun tasksIdDelete( @PathVariable("id") id: Int
    ): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.tasksIdDelete(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks/{id}"],
        produces = ["application/json"]
    )
    suspend fun tasksIdGet( @PathVariable("id") id: Int
    ): ResponseEntity<TaskDto> {
        return ResponseEntity(service.tasksIdGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks/{id}/input"],
        produces = ["multipart/form-data"]
    )
    suspend fun tasksIdInputGet( @PathVariable("id") id: Int
    ): ResponseEntity<MultiInputDto> {
        return ResponseEntity(service.tasksIdInputGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/tasks"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    suspend fun tasksPost( @RequestBody taskDto: TaskDto
    ): ResponseEntity<IdObjectDto> {
        return ResponseEntity(service.tasksPost(taskDto), HttpStatus.valueOf(200))
    }
}
