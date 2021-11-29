package hu.bme.mit.theta.restapi.api.workers

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto

interface WorkersApiService {

    suspend fun workersGet(): List<WorkerDto>

    suspend fun workersIdDelete(id: Int): IdObjectDto

    suspend fun workersIdGet(id: Int): WorkerDto

    suspend fun workersIdPut(workerDto: WorkerDto): IdObjectDto

    suspend fun workersPost(workerDto: WorkerDto): IdObjectDto
}
