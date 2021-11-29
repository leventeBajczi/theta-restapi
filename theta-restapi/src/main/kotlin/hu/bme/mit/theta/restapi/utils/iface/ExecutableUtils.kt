package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto

interface ExecutableUtils {
    fun getStatus(s: String): ExecutableDto
    fun updateExecutable(s: String, executable: ExecutableDto): ExecutableDto
}