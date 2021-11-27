package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Worker
import kotlinx.coroutines.flow.Flow;

interface WorkersApiService {

    fun workersGet(): Flow<Worker>

    suspend fun workersIdDelete(id: Int): IdObject

    suspend fun workersIdGet(id: Int): Worker

    suspend fun workersIdPut(id: Int, id2: Int, address: String, name: String?): IdObject

    suspend fun workersPost(id: Int, address: String, name: String?): IdObject
}
