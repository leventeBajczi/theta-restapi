package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.StaticResources
import kotlinx.coroutines.flow.Flow;

interface ResourcesApiService {

    suspend fun resourcesGet(): StaticResources
}
