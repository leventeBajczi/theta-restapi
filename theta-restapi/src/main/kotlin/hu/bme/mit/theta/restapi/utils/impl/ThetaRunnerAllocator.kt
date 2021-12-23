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
    private val workers = 1
    private val executorService: ExecutorService = Executors.newFixedThreadPool(workers)
    var counter = 0

    override fun runTask(task: Task) {
        synchronized(executorService) {
            when {
                task.useScheduling && counter >= workers  -> taskSchedulerRunner.runTask(task)
                task.useRunexec -> {
                    counter++
                    executorService.submit {
                        runexecRunner.runTask(task)
                        counter--
                    }
                }
                else -> {
                    counter++
                    executorService.submit {
                        directThetaRunner.runTask(task)
                        counter--
                    }
                }
            }
        }
    }
}