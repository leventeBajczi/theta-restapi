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
        val timeLimit = task.timeoutS
        val inputs = task.inputIds.map { fileRepository.findById(it).orElseThrow() }.associateBy({it.name}, {it.fullPath})
        val params = task.parameters.map { inputs.getOrDefault(it, it) }.toTypedArray()
        val executable = executableUtils.getExecutableWithPath("theta.zip")

        val command = arrayOf(executable, *params)
        val stopwatch = StopWatch()
        stopwatch.start()
        val (stdout, stderr) = command.runCommand(File(config.tmp), timeLimit.toLong(), TimeUnit.SECONDS)
        val newTask: Task = task.copy(usedTimeS = stopwatch.totalTimeSeconds.toInt(), stderr = stderr, stdout = stdout)
        taskRepository.save(newTask)
    }
}