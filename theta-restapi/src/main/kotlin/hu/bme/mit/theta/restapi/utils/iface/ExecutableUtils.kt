package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutExecutableDto

interface ExecutableUtils {
    fun getStatus(s: String): OutExecutableDto
    fun updateExecutable(s: String, executable: InExecutableDto): OutExecutableDto

    fun getExecutableWithPath(s: String): String
}