package hu.bme.mit.theta.restapi.utils.impl

import java.io.File
import java.util.concurrent.TimeUnit

fun Array<String>.runCommand(timeout: Long = -1, timeUnit: TimeUnit = TimeUnit.SECONDS): Pair<File, File> {
    val stdoutFile = File.createTempFile("stdout", ".txt")
    val stderrFile = File.createTempFile("stderr", ".txt")
    try {
        val proc = ProcessBuilder(*this)
            .redirectOutput(ProcessBuilder.Redirect.appendTo(stdoutFile))
            .redirectError(ProcessBuilder.Redirect.appendTo(stderrFile))
            .start()
        if(timeout > 0) proc.waitFor(timeout, timeUnit)
        else proc.waitFor()
    } catch (t: Throwable) {
        stderrFile.appendText(t.localizedMessage)
    }
    return Pair(stdoutFile, stderrFile)
}
fun String.runCommand(timeout: Long = -1, timeUnit: TimeUnit = TimeUnit.SECONDS): Pair<File, File> = arrayOf(this).runCommand(timeout, timeUnit)