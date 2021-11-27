package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto

interface ExecutablesApiService {

    suspend fun runexecGet(): ExecutableDto

    suspend fun runexecPut(version: String, description: String, binary: String, commit: String?): ExecutableDto

    suspend fun thetaGet(): ExecutableDto

    suspend fun thetaPut(version: String, description: String, binary: String, commit: String?): ExecutableDto
}
