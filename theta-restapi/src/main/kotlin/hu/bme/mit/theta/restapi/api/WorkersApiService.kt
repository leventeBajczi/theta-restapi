package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import kotlinx.coroutines.flow.Flow;

interface WorkersApiService {

    fun workersGet(): Flow<WorkerDto>

    suspend fun workersIdDelete(id: Int): IdObjectDto

    suspend fun workersIdGet(id: Int): WorkerDto

    suspend fun workersIdPut(id: Int, id2: Int, address: String, name: String?): IdObjectDto

    suspend fun workersPost(id: Int, address: String, name: String?): IdObjectDto
}
