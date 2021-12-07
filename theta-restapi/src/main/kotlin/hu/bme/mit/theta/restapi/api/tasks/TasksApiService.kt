package hu.bme.mit.theta.restapi.api.tasks

import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.input.InTaskDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutTaskDto

interface TasksApiService {

    suspend fun tasksGet(): List<OutTaskDto>

    suspend fun tasksIdDelete(id: Int): IdObjectDto

    suspend fun tasksIdGet(id: Int): OutTaskDto

    suspend fun tasksIdInputGet(id: Int): MultiInputDto

    suspend fun tasksPost(task: InTaskDto): IdObjectDto
}
