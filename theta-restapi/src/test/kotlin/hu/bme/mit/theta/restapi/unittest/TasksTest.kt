package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.tasks.TasksApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.membersEqual
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.inout.SingleInputDto
import hu.bme.mit.theta.restapi.model.dtos.input.InTaskDto
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@SpringBootTest
@Transactional
@ConfigurationPropertiesBinding
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class TasksTest(@Autowired val tasksApiService: TasksApiService) {
    @Test
    @Order(0)
    fun testEmptyGet() {
        runBlocking {
            val tasks = tasksApiService.tasksGet()
            Assertions.assertEquals(emptyList<InTaskDto>(), tasks)
        }
    }

    @Test
    @Order(1)
    fun testPost() {
        runBlocking {
            val task = InTaskDto(input= MultiInputDto(emptyList()),userId = 0)
            val preTasks = tasksApiService.tasksGet()
            Assertions.assertFalse(preTasks.any { membersEqual(it, task) })
            tasksApiService.tasksPost(task)
            val postTasks = tasksApiService.tasksGet()
            Assertions.assertTrue(postTasks.any { membersEqual(it, task) })
        }
    }

    @Test
    @Order(2)
    fun testGetById() {
        runBlocking {
            val task = InTaskDto(input= MultiInputDto(emptyList()),userId = 0)
            val postedTaskId = tasksApiService.tasksPost(task)
            val storedTask = tasksApiService.tasksIdGet(postedTaskId.id!!)
            Assertions.assertTrue(membersEqual(storedTask, task), Pair(storedTask, task).toString())
        }
    }

    @Test
    @Order(3)
    fun testDelete() {
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                val task = InTaskDto(input = MultiInputDto(emptyList()), userId = 0)
                val postedTaskId = tasksApiService.tasksPost(task)
                val deletedTaskId = tasksApiService.tasksIdDelete(postedTaskId.id!!)
                Assertions.assertTrue(membersEqual(postedTaskId, deletedTaskId), Pair(postedTaskId, deletedTaskId).toString())
                tasksApiService.tasksIdDelete(postedTaskId.id!!)
                tasksApiService.tasksIdGet(deletedTaskId.id!!)
            }
        }
    }

    @Test
    @Order(4)
    fun testInput() {
        runBlocking {
            val inputs = MultiInputDto(listOf(
                SingleInputDto(name="Input1",content="Q29udGVudDE="),
                SingleInputDto(name="Input2",content="Q29udGVudDI="),
            ))
            val task = InTaskDto(input = inputs, userId = 0)
            val postedTaskId = tasksApiService.tasksPost(task)
            val retrievedInputs = tasksApiService.tasksIdInputGet(postedTaskId.id!!)
            for ((index, input) in inputs.inputs.withIndex()) {
                Assertions.assertTrue(membersEqual(input, retrievedInputs.inputs[index]), Pair(input, retrievedInputs.inputs[index]).toString())
            }
        }
    }
}