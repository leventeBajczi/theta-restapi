package hu.bme.mit.theta.restapi.api.executables

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExecutablesApiServiceImpl(
    @Autowired val executableUtils: ExecutableUtils
) : ExecutablesApiService {

    override suspend fun runexecGet(): ExecutableDto {
        return executableUtils.getStatus("runexec.zip")
    }

    override suspend fun runexecPut(executableDto: ExecutableDto): ExecutableDto {
        return executableUtils.updateExecutable("runexec.zip", executableDto)
    }

    override suspend fun thetaGet(): ExecutableDto {
        return executableUtils.getStatus("theta.zip")
    }

    override suspend fun thetaPut(executableDto: ExecutableDto): ExecutableDto {
        return executableUtils.updateExecutable("theta.zip", executableDto)
    }
}
