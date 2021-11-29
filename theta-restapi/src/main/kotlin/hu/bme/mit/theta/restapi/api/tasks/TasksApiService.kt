package hu.bme.mit.theta.restapi.api.tasks

import hu.bme.mit.theta.restapi.model.dtos.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import kotlinx.coroutines.flow.Flow

interface TasksApiService {

    fun tasksGet(): Flow<TaskDto>

    suspend fun tasksIdDelete(id: Int): IdObjectDto

    suspend fun tasksIdGet(id: Int): TaskDto

    suspend fun tasksIdInputGet(id: Int): MultiInputDto

    suspend fun tasksPost(task: TaskDto): IdObjectDto
}
