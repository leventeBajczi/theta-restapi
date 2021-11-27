package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.Executable
import kotlinx.coroutines.flow.Flow;
import org.springframework.stereotype.Service
@Service
class ExecutablesApiServiceImpl : ExecutablesApiService {

    override suspend fun runexecGet(): Executable {
        TODO("Implement me")
    }

    override suspend fun runexecPut(version: String, description: String, binary: String, commit: String?): Executable {
        TODO("Implement me")
    }

    override suspend fun thetaGet(): Executable {
        TODO("Implement me")
    }

    override suspend fun thetaPut(version: String, description: String, binary: String, commit: String?): Executable {
        TODO("Implement me")
    }
}
