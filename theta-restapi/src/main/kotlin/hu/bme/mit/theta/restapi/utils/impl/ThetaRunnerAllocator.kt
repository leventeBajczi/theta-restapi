package hu.bme.mit.theta.restapi.utils.impl

import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.utils.iface.ThetaRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Component
class ThetaRunnerAllocator(
    @Autowired
    val taskSchedulerRunner: TaskSchedulerRunner,
    @Autowired
    val runexecRunner: RunexecRunner,
    @Autowired
    val directThetaRunner: DirectThetaRunner,
) : ThetaRunner {
    override fun runTask(task: Task) = when {
        task.useScheduling -> taskSchedulerRunner.runTask(task)
        task.useRunexec -> runexecRunner.runTask(task)
        else -> directThetaRunner.runTask(task)
    }
}