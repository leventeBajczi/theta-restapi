package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.StaticResources

interface ResourcesApiService {

    suspend fun resourcesGet(): StaticResources
}
