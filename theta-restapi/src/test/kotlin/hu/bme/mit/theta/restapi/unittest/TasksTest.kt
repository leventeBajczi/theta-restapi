package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.tasks.TasksApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.SingleInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import javax.transaction.Transactional
import kotlin.reflect.full.memberProperties

@Component
@SpringBootTest
@Transactional
@ConfigurationPropertiesBinding
class TasksTest(@Autowired val tasksApiService: TasksApiService) {
    private fun taskEquals(task1: TaskDto, task2: TaskDto) : Boolean {
        for(prop in TaskDto::class.memberProperties) {
            val task1Prop = prop.get(task1)
            val task2Prop = prop.get(task2)
            if(task1Prop != null && task2Prop != null && task1Prop != task2Prop)
                return false
        }
        return true
    }


    @Test
    fun testEmptyGet() {
        runBlocking {
            val tasks = tasksApiService.tasksGet()
            Assertions.assertEquals(emptyList<TaskDto>(), tasks)
        }
    }

    @Test
    fun testPost() {
        runBlocking {
            val task = TaskDto(input= MultiInputDto(emptyList()),userId = 0)
            val preTasks = tasksApiService.tasksGet()
            Assertions.assertFalse(preTasks.any { taskEquals(it, task) })
            tasksApiService.tasksPost(task)
            val postTasks = tasksApiService.tasksGet()
            Assertions.assertTrue(postTasks.any { taskEquals(it, task) })
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            val task = TaskDto(input= MultiInputDto(emptyList()),userId = 0)
            val postedTaskId = tasksApiService.tasksPost(task)
            val storedTask = tasksApiService.tasksIdGet(postedTaskId.id!!)
            Assertions.assertTrue(taskEquals(task, storedTask))
        }
    }

    @Test
    fun testDelete() {
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                val task = TaskDto(input = MultiInputDto(emptyList()), userId = 0)
                val postedTaskId = tasksApiService.tasksPost(task)
                val deletedTaskId = tasksApiService.tasksIdDelete(postedTaskId.id!!)
                Assertions.assertEquals(postedTaskId, deletedTaskId)
                tasksApiService.tasksIdDelete(postedTaskId.id!!)
                tasksApiService.tasksIdGet(deletedTaskId.id!!)
            }
        }
    }

    @Test
    fun testInput() {
        runBlocking {
            val inputs = MultiInputDto(listOf(
                SingleInputDto(name="Input1",content="Content1"),
                SingleInputDto(name="Input2",content="Content2"),
            ))
            val task = TaskDto(input = inputs, userId = 0)
            val postedTaskId = tasksApiService.tasksPost(task)
            val retrievedInputs = tasksApiService.tasksIdInputGet(postedTaskId.id!!)
            Assertions.assertEquals(inputs, retrievedInputs)
        }
    }
}