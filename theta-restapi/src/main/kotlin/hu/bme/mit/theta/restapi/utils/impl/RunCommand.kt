package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.exceptions.CommandException
import java.io.File
import java.util.concurrent.TimeUnit

fun Array<String>.runCommand(folder: File, timeout: Long = -1, timeUnit: TimeUnit = TimeUnit.SECONDS, shouldThrow: Boolean = false): Pair<File, File> {
    File("tmp").mkdirs()
    val stdoutFile = File.createTempFile("stdout", ".txt", folder)
    val stderrFile = File.createTempFile("stderr", ".txt", folder)
    val proc = try {
        val proc = ProcessBuilder(*this)
            .redirectOutput(ProcessBuilder.Redirect.appendTo(stdoutFile))
            .redirectError(ProcessBuilder.Redirect.appendTo(stderrFile))
            .start()
        if(timeout > 0) proc.waitFor(timeout, timeUnit)
        else proc.waitFor()
        proc
    } catch (t: Throwable) {
        stderrFile.appendText(t.localizedMessage)
        if(shouldThrow) throw t
        null
    }
    if(shouldThrow && proc?.exitValue() != 0) throw CommandException("Process {$this} exited with status code ${proc?.exitValue()}")
    return Pair(stdoutFile, stderrFile)
}
fun String.runCommand(folder: File, timeout: Long = -1, timeUnit: TimeUnit = TimeUnit.SECONDS, shouldThrow: Boolean = false): Pair<File, File> = arrayOf(this).runCommand(folder, timeout, timeUnit, shouldThrow)