package hu.bme.mit.theta.restapi.api.resources

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto

interface ResourcesApiService {

    suspend fun resourcesGet(): StaticResourcesDto
}
