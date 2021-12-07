package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.model.dtos.output.OutStaticResourcesDto
import hu.bme.mit.theta.restapi.utils.iface.ResourceUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File

@Component
class GnuLinuxResourceUtils(@Autowired val config: ApplicationConfiguration) : ResourceUtils {
    override fun getResources(): OutStaticResourcesDto {
        val nProc = "nproc".runCommand(File(config.tmp)).first.readText().replace("\\s".toRegex(), "")
        val ram = arrayOf("sh", "-c", "free --mega | grep Mem | awk '{ print \$2 }'").runCommand(File(config.tmp)).first.readText().replace("\\s".toRegex(), "")
        return OutStaticResourcesDto(logicalCpu = nProc.toInt(), ramM = ram.toInt())
    }
}