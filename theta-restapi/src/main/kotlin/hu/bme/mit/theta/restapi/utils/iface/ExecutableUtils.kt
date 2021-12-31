package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutExecutableDto
import java.io.File

interface ExecutableUtils {
    fun getStatus(s: String): OutExecutableDto
    fun updateExecutable(s: String, executable: InExecutableDto): OutExecutableDto

    fun getExecutableWithPath(s: String, version: String? = null): String

    fun getAllExecutableVersions(s: String): Map<String, File>

    fun getRelativePath(s: String, version: String? = null): String
    fun getArchive(s: String, version: String? = null): File
}