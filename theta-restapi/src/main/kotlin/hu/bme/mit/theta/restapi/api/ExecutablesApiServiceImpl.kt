package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto
import org.springframework.stereotype.Service

@Service
class ExecutablesApiServiceImpl : ExecutablesApiService {

    override suspend fun runexecGet(): ExecutableDto {
        TODO("Implement me")
    }

    override suspend fun runexecPut(version: String, description: String, binary: String, commit: String?): ExecutableDto {
        TODO("Implement me")
    }

    override suspend fun thetaGet(): ExecutableDto {
        TODO("Implement me")
    }

    override suspend fun thetaPut(version: String, description: String, binary: String, commit: String?): ExecutableDto {
        TODO("Implement me")
    }
}
