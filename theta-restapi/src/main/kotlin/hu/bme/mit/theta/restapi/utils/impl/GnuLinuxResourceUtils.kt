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
        val nprocOutput = "nproc".runCommand(File(config.tmp))
        val ramOutput = arrayOf("sh", "-c", "free --mega | grep Mem | awk '{ print \$2 }'").runCommand(File(config.tmp))
        val nProc = nprocOutput.first.readText().replace("\\s".toRegex(), "")
        val ram = ramOutput.first.readText().replace("\\s".toRegex(), "")
        nprocOutput.first.delete()
        nprocOutput.second.delete()
        ramOutput.first.delete()
        ramOutput.second.delete()
        return OutStaticResourcesDto(logicalCpu = nProc.toInt(), ramM = ram.toInt())
    }
}