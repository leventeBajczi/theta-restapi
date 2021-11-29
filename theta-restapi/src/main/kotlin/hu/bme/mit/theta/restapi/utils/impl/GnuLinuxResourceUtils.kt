package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.model.dtos.StaticResourcesDto
import hu.bme.mit.theta.restapi.utils.iface.ResourceUtils
import org.springframework.stereotype.Component

@Component
class GnuLinuxResourceUtils : ResourceUtils {
    override fun getResources(): StaticResourcesDto {
        val nProc = "nproc".runCommand()?.replace("\\s".toRegex(), "")
        val ram = arrayOf("bash", "-c", "free --mega | grep Mem | awk '{ print \$2 }'").runCommand()?.replace("\\s".toRegex(), "")
        return StaticResourcesDto(logicalCpu = nProc?.toInt(), ramM = ram?.toInt())
    }
}