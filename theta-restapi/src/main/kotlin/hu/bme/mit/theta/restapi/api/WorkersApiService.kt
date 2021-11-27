package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Worker
import kotlinx.coroutines.flow.Flow;

interface WorkersApiService {

    fun workersGet(): Flow<Worker>

    suspend fun workersIdDelete(id: kotlin.Int): IdObject

    suspend fun workersIdGet(id: kotlin.Int): Worker

    suspend fun workersIdPut(id: kotlin.Int, id2: kotlin.Int, address: kotlin.String, name: kotlin.String?): IdObject

    suspend fun workersPost(id: kotlin.Int, address: kotlin.String, name: kotlin.String?): IdObject
}
