package hu.bme.mit.theta.restapi.api.resources

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
@RequestMapping("\${api.base-path:}")
class ResourcesApiController(@Autowired(required = true) val service: ResourcesApiService) {


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/resources"],
        produces = ["application/json"]
    )
    suspend fun resourcesGet(): ResponseEntity<StaticResourcesDto> {
        return ResponseEntity(service.resourcesGet(), HttpStatus.valueOf(200))
    }
}
