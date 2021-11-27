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

    override suspend fun workersIdDelete(id: kotlin.Int): IdObject {
        TODO("Implement me")
    }

    override suspend fun workersIdGet(id: kotlin.Int): Worker {
        TODO("Implement me")
    }

    override suspend fun workersIdPut(id: kotlin.Int, id2: kotlin.Int, address: kotlin.String, name: kotlin.String?): IdObject {
        TODO("Implement me")
    }

    override suspend fun workersPost(id: kotlin.Int, address: kotlin.String, name: kotlin.String?): IdObject {
        TODO("Implement me")
    }
}
