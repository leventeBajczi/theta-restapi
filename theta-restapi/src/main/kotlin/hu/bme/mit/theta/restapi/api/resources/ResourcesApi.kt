package hu.bme.mit.theta.restapi.api.resources

import hu.bme.mit.theta.restapi.api.handleRESTStatus
import hu.bme.mit.theta.restapi.model.dtos.input.InStaticResourcesDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutStaticResourcesDto
import org.springframework.beans.factory.annotation.Autowired
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
    suspend fun resourcesGet(): ResponseEntity<OutStaticResourcesDto> {
        return handleRESTStatus {service.resourcesGet()}
    }
}
