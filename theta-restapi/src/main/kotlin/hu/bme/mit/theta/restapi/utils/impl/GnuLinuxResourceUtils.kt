package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.model.dtos.output.OutStaticResourcesDto
import hu.bme.mit.theta.restapi.utils.iface.ResourceUtils
import org.springframework.stereotype.Component

@Component
class GnuLinuxResourceUtils : ResourceUtils {
    override fun getResources(): OutStaticResourcesDto {
        val nProc = "nproc".runCommand().first.readText().replace("\\s".toRegex(), "")
        val ram = arrayOf("sh", "-c", "free --mega | grep Mem | awk '{ print \$2 }'").runCommand().first.readText().replace("\\s".toRegex(), "")
        return OutStaticResourcesDto(logicalCpu = nProc.toInt(), ramM = ram.toInt())
    }
}