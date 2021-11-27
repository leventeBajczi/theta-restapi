package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.IdObject
import hu.bme.mit.theta.restapi.model.Worker
import kotlinx.coroutines.flow.Flow;
import org.springframework.stereotype.Service
@Service
class WorkersApiServiceImpl : WorkersApiService {

    override fun workersGet(): Flow<Worker> {
        TODO("Implement me")
    }

    override suspend fun workersIdDelete(id: Int): IdObject {
        TODO("Implement me")
    }

    override suspend fun workersIdGet(id: Int): Worker {
        TODO("Implement me")
    }

    override suspend fun workersIdPut(id: Int, id2: Int, address: String, name: String?): IdObject {
        TODO("Implement me")
    }

    override suspend fun workersPost(id: Int, address: String, name: String?): IdObject {
        TODO("Implement me")
    }
}
