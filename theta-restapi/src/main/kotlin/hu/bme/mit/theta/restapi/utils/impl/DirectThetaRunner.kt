package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.repository.FileRepository
import hu.bme.mit.theta.restapi.repository.TaskRepository
import hu.bme.mit.theta.restapi.utils.iface.ExecutableUtils
import hu.bme.mit.theta.restapi.utils.iface.ThetaRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import java.io.File
import java.util.concurrent.TimeUnit

@Component
class DirectThetaRunner(
    @Autowired val taskRepository: TaskRepository,
    @Autowired val fileRepository: FileRepository,
    @Autowired val executableUtils: ExecutableUtils,
    @Autowired val config: ApplicationConfiguration
    ) : ThetaRunner {
    override fun runTask(
        task: Task
    ) {
        try {
            println("Running task $task")
            val timeLimit = task.timeoutS
            val inputs = task.inputIds.map { fileRepository.findById(it).orElseThrow() }
                .associateBy({ it.name }, { it.fullPath })
            val params = task.parameters.map { inputs.getOrDefault(it, it) }.toTypedArray()
            val executable = executableUtils.getExecutableWithPath("theta.zip", task.toolVersion)

            val command = arrayOf(executable, *params)
            val stopwatch = StopWatch()
            stopwatch.start()
            val (stdout, stderr) = command.runCommand(File(config.tmp), timeLimit.toLong(), TimeUnit.SECONDS)
            val newTask: Task = task.copy(usedTimeS = stopwatch.totalTimeSeconds, stderr = stderr, stdout = stdout)
            taskRepository.save(newTask)
        } catch(e: Exception) {
            e.printStackTrace()
            val tmpFile = File.createTempFile("stderr", ".txt", File(config.tmp))
            tmpFile.appendText(e.stackTraceToString())
            val newTask: Task = task.copy(
                stderr = tmpFile,
                retval = -1
            )
            taskRepository.save(newTask)
        }
    }
}