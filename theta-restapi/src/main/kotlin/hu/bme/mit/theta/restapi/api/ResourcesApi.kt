package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.StaticResources
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
class ResourcesApiController(@Autowired(required = true) val service: ResourcesApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/resources"],
        produces = ["application/json"]
    )
    suspend fun resourcesGet(): ResponseEntity<StaticResources> {
        return ResponseEntity(service.resourcesGet(), HttpStatus.valueOf(200))
    }
}
