package hu.bme.mit.theta.restapi.api

import hu.bme.mit.theta.restapi.model.Executable
import kotlinx.coroutines.flow.Flow;

interface ExecutablesApiService {

    suspend fun runexecGet(): Executable

    suspend fun runexecPut(version: kotlin.String, description: kotlin.String, binary: kotlin.String, commit: kotlin.String?): Executable

    suspend fun thetaGet(): Executable

    suspend fun thetaPut(version: kotlin.String, description: kotlin.String, binary: kotlin.String, commit: kotlin.String?): Executable
}
