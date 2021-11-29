package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.model.dtos.ExecutableDto
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@Component
class GnuLinuxExecutableUtils(val sync: Any = Any()) : ExecutableUtils {
    override fun getStatus(s: String): ExecutableDto {
        synchronized(sync) {
            val folder = File(s.substring(0, s.lastIndexOf(".")))
            if (!(folder.exists() && folder.isDirectory)) {
                throw NoSuchElementException(s + " has no installed candidate.")
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
            return ExecutableDto(version = version, commit = commit, description = description)
        }
    }

    override fun updateExecutable(s: String, executable: ExecutableDto) : ExecutableDto{
        synchronized(sync) {
            val folder = File(s.substring(0, s.lastIndexOf(".")))
            if (folder.exists() && folder.isDirectory) {
                folder.deleteRecursively()
            }
            folder.mkdirs()
            val file = File(s)
            file.delete()
            file.writeBytes(executable.binaryBytes!!)
            "unzip ${file.absolutePath} -d ${folder.absolutePath}".runCommand(1, TimeUnit.MINUTES)
            File(folder.path + "." + "description.txt").writeText(executable.description)
            if(executable.commit != null) File(folder.path + "." + "commit.txt").writeText(executable.commit)
            File(folder.path + "." + "version.txt").writeText(executable.version)

            return getStatus(s)
        }

    }
}