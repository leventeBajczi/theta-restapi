package hu.bme.mit.theta.restapi.api.executables

import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutExecutableDto

interface ExecutablesApiService {

    suspend fun runexecGet(): OutExecutableDto

    suspend fun runexecVersions(): List<String>

    suspend fun runexecPut(executableDto: InExecutableDto): OutExecutableDto

    suspend fun thetaGet(): OutExecutableDto

    suspend fun thetaVersions(): List<String>

    suspend fun thetaPut(executableDto: InExecutableDto): OutExecutableDto
}
