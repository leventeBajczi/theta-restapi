package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutExecutableDto
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileFilter
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
                File(folder.path + File.separator + "version.txt").readText()
            } catch (e: IOException) {
                "unkown"
            }
            val relativePath = try {
                File(folder.path + File.separator + "relativePath.txt").readText()
            } catch (e: IOException) {
                "unkown"
            }
            return OutExecutableDto(version = version, relativePath = relativePath)
        }
    }

    override fun updateExecutable(s: String, executable: InExecutableDto) : OutExecutableDto {
        synchronized(sync) {
            val folder = File(config.executables + File.separator + s.substring(0, s.lastIndexOf(".")) + executable.version)
            val latestFolder = File(config.executables + File.separator + s.substring(0, s.lastIndexOf(".")))
            if(latestFolder.exists()) latestFolder.deleteRecursively()
            if (folder.exists() && folder.isDirectory) {
                folder.deleteRecursively()
            }
            folder.mkdirs()
            val file = File(config.executables + File.separator + s)
            if(file.exists()) file.delete()
            file.writeBytes(executable.binaryBytes!!)
            arrayOf("unzip",file.absolutePath,"-d",folder.absolutePath).runCommand(File(config.tmp), 1, TimeUnit.MINUTES)
            arrayOf("ln", "-sf", folder.absolutePath, latestFolder.absolutePath).runCommand(File(config.tmp))
            File(folder.path + File.separator + "relativePath.txt").writeText(executable.relativePath)
            File(folder.path + File.separator + "version.txt").writeText(executable.version)

            return getStatus(s)
        }

    }

    override fun getExecutableWithPath(s: String, version: String?): String {
        val path = if(version == null) {
            config.executables +
            File.separator +
            s.substring(0, s.lastIndexOf("."))
        } else {
            config.executables +
            File.separator +
            s.substring(0, s.lastIndexOf(".")) + version
        }
        return path +
                File.separator +
                File("$path${File.separator}relativePath.txt").readText()
    }

    override fun getAllExecutableVersions(s: String): Map<String, File> {
        val prefix =  s.substring(0, s.lastIndexOf("."))
        val ret: MutableMap<String, File> = linkedMapOf()
        for (version in File(config.executables).listFiles(FileFilter {
            it.name.startsWith(prefix)
        }) ?: arrayOf()) {
            val versionString = version.name.substring(prefix.length)
            ret[versionString] = File(getExecutableWithPath(s, versionString))
        }
        return ret
    }

    override fun getRelativePath(s: String, version: String?): String {
        val path = if(version == null) {
            config.executables +
                    File.separator +
                    s.substring(0, s.lastIndexOf("."))
        } else {
            config.executables +
                    File.separator +
                    s.substring(0, s.lastIndexOf(".")) + version
        }
        return File("$path${File.separator}relativePath.txt").readText()
    }
}