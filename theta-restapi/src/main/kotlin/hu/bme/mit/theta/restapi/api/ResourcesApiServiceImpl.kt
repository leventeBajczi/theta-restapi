package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto
import hu.bme.mit.theta.restapi.utils.iface.ResourceUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourcesApiServiceImpl(@Autowired val resourceUtils: ResourceUtils) : ResourcesApiService {


    override suspend fun resourcesGet(): StaticResourcesDto {
        return resourceUtils.getResources()
    }
}
