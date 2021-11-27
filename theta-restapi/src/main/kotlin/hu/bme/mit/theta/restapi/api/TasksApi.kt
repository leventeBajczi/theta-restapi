package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.*
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
    fun tasksGet(): ResponseEntity<Flow<Task>> {
        return ResponseEntity(service.tasksGet(), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/tasks/{id}"],
        produces = ["application/json"]
    )
    suspend fun tasksIdDelete( @PathVariable("id") id: Int
): ResponseEntity<IdObject> {
        return ResponseEntity(service.tasksIdDelete(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks/{id}"],
        produces = ["application/json"]
    )
    suspend fun tasksIdGet( @PathVariable("id") id: Int
): ResponseEntity<Task> {
        return ResponseEntity(service.tasksIdGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/tasks/{id}/input"],
        produces = ["multipart/form-data"]
    )
    suspend fun tasksIdInputGet( @PathVariable("id") id: Int
): ResponseEntity<Input> {
        return ResponseEntity(service.tasksIdInputGet(id), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/tasks/{id}"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun tasksIdPut( @PathVariable("id") id: Int
, @RequestParam(value="id", required=true) id2: Int
, @RequestParam(value="timestamp", required=true) timestamp: java.time.OffsetDateTime 
, @RequestParam(value="input", required=true) input: Input 
, @RequestParam(value="user", required=true) user: User 
, @RequestParam(value="parameters", required=false) parameters: List<String>?
, @RequestParam(value="priority", required=false) priority: String
, @RequestParam(value="benchmark", required=false) benchmark: TaskBenchmark? 
): ResponseEntity<IdObject> {
        return ResponseEntity(service.tasksIdPut(id, id2, timestamp, input, user, parameters, priority, benchmark), HttpStatus.valueOf(200))
    }


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/tasks"],
        produces = ["application/json"],
        consumes = ["multipart/form-data"]
    )
    suspend fun tasksPost( @RequestParam(value="id", required=true) id: Int
, @RequestParam(value="timestamp", required=true) timestamp: java.time.OffsetDateTime 
, @RequestParam(value="input", required=true) input: Input 
, @RequestParam(value="user", required=true) user: User 
, @RequestParam(value="parameters", required=false) parameters: List<String>?
, @RequestParam(value="priority", required=false) priority: String
, @RequestParam(value="benchmark", required=false) benchmark: TaskBenchmark? 
): ResponseEntity<IdObject> {
        return ResponseEntity(service.tasksPost(id, timestamp, input, user, parameters, priority, benchmark), HttpStatus.valueOf(200))
    }
}
