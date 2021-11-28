package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto

interface ExecutableUtils {
    fun getStatus() : ExecutableDto
    fun updateExecutable(executable: ExecutableDto)
}