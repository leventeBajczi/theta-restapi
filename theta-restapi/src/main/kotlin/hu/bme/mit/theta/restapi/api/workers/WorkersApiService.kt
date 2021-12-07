package hu.bme.mit.theta.restapi.api.workers

import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.input.InWorkerDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutWorkerDto

interface WorkersApiService {

    suspend fun workersGet(): List<OutWorkerDto>

    suspend fun workersIdDelete(id: Int): IdObjectDto

    suspend fun workersIdGet(id: Int): OutWorkerDto

    suspend fun workersIdPut(workerDto: InWorkerDto, id: Int = 0): IdObjectDto

    suspend fun workersPost(workerDto: InWorkerDto): IdObjectDto
}
