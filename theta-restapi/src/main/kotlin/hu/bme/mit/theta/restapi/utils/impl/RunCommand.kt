package hu.bme.mit.theta.restapi.utils.impl

import java.io.IOException
import java.util.concurrent.TimeUnit

fun String.runCommand(timeout: Long, timeUnit: TimeUnit): String? {
    try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(*parts.toTypedArray())
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(timeout, timeUnit)
        return proc.inputStream.bufferedReader().readText()
    } catch(e: IOException) {
        e.printStackTrace()
        return null
    }
}