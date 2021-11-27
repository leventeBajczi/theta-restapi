package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto
import org.springframework.stereotype.Service

@Service
class ResourcesApiServiceImpl : ResourcesApiService {

    override suspend fun resourcesGet(): StaticResourcesDto {
        TODO("Implement me")
    }
}
