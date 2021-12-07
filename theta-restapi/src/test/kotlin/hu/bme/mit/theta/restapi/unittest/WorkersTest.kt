package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.workers.WorkersApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.membersEqual
import hu.bme.mit.theta.restapi.model.dtos.input.InWorkerDto
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@SpringBootTest
@Transactional
@ConfigurationPropertiesBinding
class WorkersTest(@Autowired val workersApiService: WorkersApiService) {
    @Test
    fun testEmptyGet() {
        runBlocking {
            val tasks = workersApiService.workersGet()
            Assertions.assertEquals(emptyList<InWorkerDto>(), tasks)
        }
    }

    @Test
    fun testPost() {
        runBlocking {
            val worker = InWorkerDto(address="Address")
            val preWorkers = workersApiService.workersGet()
            Assertions.assertFalse(preWorkers.any { membersEqual(it, worker) })
            workersApiService.workersPost(worker)
            val postWorkers = workersApiService.workersGet()
            Assertions.assertTrue(postWorkers.any { membersEqual(it, worker) })
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            val worker = InWorkerDto(address="Address")
            val postedWorkerId = workersApiService.workersPost(worker)
            Assertions.assertTrue(membersEqual(workersApiService.workersIdGet(postedWorkerId.id!!), worker))
        }
    }

    @Test
    fun testDelete() {
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                val worker = InWorkerDto(address="Address")
                val postedWorkerId = workersApiService.workersPost(worker)
                val deletedWorkerId = workersApiService.workersIdDelete(postedWorkerId.id!!)
                Assertions.assertEquals(postedWorkerId, deletedWorkerId)
                workersApiService.workersIdGet(deletedWorkerId.id!!)
            }
        }
    }

    @Test
    fun testPut() {
        runBlocking {
            val worker = InWorkerDto(address="Address")
            val postedWorkerId = workersApiService.workersPost(worker)
            Assertions.assertTrue(membersEqual(workersApiService.workersIdGet(postedWorkerId.id!!), worker))
            val modifiedWorker = InWorkerDto(address= "ModifiedAddress")
            val putWorkerId = workersApiService.workersIdPut(modifiedWorker, postedWorkerId.id!!)
            Assertions.assertEquals(postedWorkerId, putWorkerId)
            Assertions.assertTrue(membersEqual(workersApiService.workersIdGet(putWorkerId.id!!), modifiedWorker))
            Assertions.assertFalse(membersEqual(workersApiService.workersIdGet(putWorkerId.id!!), worker))
        }
    }
}