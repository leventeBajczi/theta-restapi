package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutExecutableDto
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@Component
class GnuLinuxExecutableUtils(@Autowired val config: ApplicationConfiguration) : ExecutableUtils {
    val sync: Any = Any()

    override fun getStatus(s: String): OutExecutableDto {
        synchronized(sync) {
            val folder = File(config.executables + File.separator + s.substring(0, s.lastIndexOf(".")))
            if (!(folder.exists() && folder.isDirectory)) {
                throw NoSuchElement(s + " has no installed candidate.")
            }
            val version = try {
                File(folder.path + "." + "version.txt").readText()
            } catch (e: IOException) {
                "unkown"
            }
            val commit = try {
                File(folder.path + "." + "commit.txt").readText()
            } catch (e: IOException) {
                null
            }
            val description = try {
                File(folder.path + "." + "description.txt").readText()
            } catch (e: IOException) {
                "unkown"
            }
            return OutExecutableDto(version = version, commit = commit, description = description)
        }
    }

    override fun updateExecutable(s: String, executable: InExecutableDto) : OutExecutableDto {
        synchronized(sync) {
            val folder = File(config.executables + File.separator + s.substring(0, s.lastIndexOf(".")))
            if (folder.exists() && folder.isDirectory) {
                folder.deleteRecursively()
            }
            folder.mkdirs()
            val file = File(config.executables + File.separator + s)
            file.delete()
            file.writeBytes(executable.binaryBytes!!)
            arrayOf("unzip",file.absolutePath,"-d",folder.absolutePath).runCommand(1, TimeUnit.MINUTES)
            File(folder.path + "." + "description.txt").writeText(executable.description)
            if(executable.commit != null) File(folder.path + "." + "commit.txt").writeText(executable.commit)
            File(folder.path + "." + "version.txt").writeText(executable.version)

            return getStatus(s)
        }

    }

    override fun getExecutableWithPath(s: String): String {
        return config.executables + File.separator + s.substring(0, s.lastIndexOf(".")) + File.separator + "run.sh"
    }
}