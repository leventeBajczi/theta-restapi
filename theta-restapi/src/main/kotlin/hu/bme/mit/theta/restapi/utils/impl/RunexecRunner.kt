package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import hu.bme.mit.theta.restapi.utils.iface.ThetaRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.util.concurrent.TimeUnit

@Component
class RunexecRunner(
    @Autowired val taskRepository: TaskRepository,
    @Autowired val fileRepository: FileRepository,
    @Autowired val executableUtils: ExecutableUtils,
    @Autowired val config: ApplicationConfiguration
    ) : ThetaRunner {
    override fun runTask(
        task: Task
    ) {
        try {
            println("Running benchmark $task")
            val inputs = task.inputIds.map { fileRepository.findById(it).orElseThrow() }
                .associateBy({ it.name }, { it.fullPath })
            val runexecParams = mutableListOf("--read-only-dir", "/", "--overlay-dir", "/home")
            if (task.ramMb > 0) {
                runexecParams.add("--memlimit")
                runexecParams.add(task.ramMb.times(1_000_000).toString())
            }
            if (task.logicalCpu > 0) {
                runexecParams.add("--cores")
                runexecParams.add("0-${(task.logicalCpu-1).toString()}")
            }
            if (task.timeoutS > 0) {
                runexecParams.add("--softtimelimit")
                runexecParams.add(task.timeoutS.toString())
                runexecParams.add("--walltimelimit")
                runexecParams.add(task.timeoutS.toString())
            }
            val stdout: File = File.createTempFile("stdout", "", File(config.tmp))
            runexecParams.add("--output")
            runexecParams.add(stdout.absolutePath)
            runexecParams.add("--")
            val params = task.parameters.map { inputs.getOrDefault(it, it) }.toTypedArray()
            val executable = executableUtils.getExecutableWithPath("runexec.zip", task.runexecVersion)
            val thetaExecutable = executableUtils.getExecutableWithPath("theta.zip", task.toolVersion)

            val command = arrayOf(executable, *runexecParams.toTypedArray(), thetaExecutable, *params)
            val (runexecStdout, _) = command.runCommand(File(config.tmp))
            var usedCpuTimeS = 0.0
            var usedWallTimeS = 0.0
            var usedRamB = 0.0
            var retval = 0
            var terminationreason: String? = null
            runexecStdout.forEachLine {
                val split = it.split("=")
                when (split[0]) {
                    "walltime" -> usedWallTimeS = split[1].substring(0 until split[1].length - 1).toDouble()
                    "cputime" -> usedCpuTimeS = split[1].substring(0 until split[1].length - 1).toDouble()
                    "memory" -> usedRamB = split[1].substring(0 until split[1].length - 1).toDouble()
                    "returnvalue" -> retval = split[1].toInt()
                    "terminationreason" -> terminationreason = split[1]
                }
            }
            val newTask: Task = task.copy(
                usedTimeS = usedWallTimeS,
                usedCpuTimeS = usedCpuTimeS,
                usedRamMb = usedRamB * 1e-6,
                stderr = null,
                stdout = stdout,
                retval = retval,
                terminationreason = terminationreason
            )
            println("Benchmark run finished ($terminationreason): $newTask")
            taskRepository.save(newTask)
        } catch(e: Exception) {
            e.printStackTrace()
            val tmpFile = File.createTempFile("stderr", ".txt", File(config.tmp))
            for (i in 1..6) tmpFile.appendText("\n")
            tmpFile.appendText(e.stackTraceToString())
            val newTask: Task = task.copy(
                usedTimeS = task.timeoutS * 1.0,
                usedCpuTimeS = task.timeoutS * 1.0,
                usedRamMb = task.usedRamMb,
                stderr = tmpFile,
                retval = -1
            )
            println("Benchmark run failed: $newTask")
            taskRepository.save(newTask)
        }
    }
}