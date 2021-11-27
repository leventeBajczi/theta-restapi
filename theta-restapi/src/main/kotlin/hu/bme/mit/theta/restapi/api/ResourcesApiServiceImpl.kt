package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.StaticResources
import org.springframework.stereotype.Service

@Service
class ResourcesApiServiceImpl : ResourcesApiService {

    override suspend fun resourcesGet(): StaticResources {
        TODO("Implement me")
    }
}
