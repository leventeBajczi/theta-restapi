package hu.bme.mit.theta.restapi.utils.impl

import java.io.IOException
import java.util.concurrent.TimeUnit

fun Array<String>.runCommand(timeout: Long = -1, timeUnit: TimeUnit = TimeUnit.SECONDS): String? {
    try {
        val proc = ProcessBuilder(*this)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        if(timeout > 0) proc.waitFor(timeout, timeUnit)
        return proc.inputStream.bufferedReader().readText()
    } catch(e: IOException) {
        e.printStackTrace()
        return null
    }
}
fun String.runCommand(timeout: Long = -1, timeUnit: TimeUnit = TimeUnit.SECONDS): String? = arrayOf(this).runCommand(timeout, timeUnit)