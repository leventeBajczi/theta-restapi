package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import kotlinx.coroutines.flow.Flow;
import org.springframework.stereotype.Service
@Service
class WorkersApiServiceImpl : WorkersApiService {

    override fun workersGet(): Flow<WorkerDto> {
        TODO("Implement me")
    }

    override suspend fun workersIdDelete(id: Int): IdObjectDto {
        TODO("Implement me")
    }

    override suspend fun workersIdGet(id: Int): WorkerDto {
        TODO("Implement me")
    }

    override suspend fun workersIdPut(workerDto: WorkerDto): IdObjectDto {
        TODO("Implement me")
    }

    override suspend fun workersPost(workerDto: WorkerDto): IdObjectDto {
        TODO("Implement me")
    }
}
