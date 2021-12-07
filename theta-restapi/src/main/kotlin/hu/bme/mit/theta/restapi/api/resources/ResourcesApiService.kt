package hu.bme.mit.theta.restapi.api.resources

import hu.bme.mit.theta.restapi.model.dtos.output.OutStaticResourcesDto

interface ResourcesApiService {

    suspend fun resourcesGet(): OutStaticResourcesDto
}
