package hu.bme.mit.theta.restapi.utils.iface

import hu.bme.mit.theta.restapi.model.entities.Task

interface ThetaRunner {
    fun runTask(task: Task)
}