package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.Executable

interface ExecutablesApiService {

    suspend fun runexecGet(): Executable

    suspend fun runexecPut(version: String, description: String, binary: String, commit: String?): Executable

    suspend fun thetaGet(): Executable

    suspend fun thetaPut(version: String, description: String, binary: String, commit: String?): Executable
}
