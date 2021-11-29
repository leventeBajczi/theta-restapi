package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto

interface ExecutablesApiService {

    suspend fun runexecGet(): ExecutableDto

    suspend fun runexecPut(executableDto: ExecutableDto): ExecutableDto

    suspend fun thetaGet(): ExecutableDto

    suspend fun thetaPut(executableDto: ExecutableDto): ExecutableDto
}
