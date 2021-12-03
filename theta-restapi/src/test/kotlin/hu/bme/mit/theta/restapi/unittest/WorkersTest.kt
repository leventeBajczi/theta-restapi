package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.workers.WorkersApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
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
class WorkersTest(@Autowired val workersApiService: WorkersApiService) {
    private fun workerEquals(worker1: WorkerDto, worker2: WorkerDto) : Boolean {
        for(prop in WorkerDto::class.memberProperties) {
            val worker1Prop = prop.get(worker1)
            val worker2Prop = prop.get(worker2)
            if(worker1Prop != null && worker2Prop != null && worker1Prop != worker2Prop)
                return false
        }
        return true
    }


    @Test
    fun testEmptyGet() {
        runBlocking {
            val tasks = workersApiService.workersGet()
            Assertions.assertEquals(emptyList<WorkerDto>(), tasks)
        }
    }

    @Test
    fun testPost() {
        runBlocking {
            val worker = WorkerDto(address="Address")
            val preWorkers = workersApiService.workersGet()
            Assertions.assertFalse(preWorkers.any { workerEquals(it, worker) })
            workersApiService.workersPost(worker)
            val postWorkers = workersApiService.workersGet()
            Assertions.assertTrue(postWorkers.any { workerEquals(it, worker) })
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            val worker = WorkerDto(address="Address")
            val postedWorkerId = workersApiService.workersPost(worker)
            Assertions.assertTrue(workerEquals(worker, workersApiService.workersIdGet(postedWorkerId.id!!)))
        }
    }

    @Test
    fun testDelete() {
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                val worker = WorkerDto(address="Address")
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
            val worker = WorkerDto(address="Address")
            val postedWorkerId = workersApiService.workersPost(worker)
            Assertions.assertTrue(workerEquals(worker, workersApiService.workersIdGet(postedWorkerId.id!!)))
            val modifiedWorker = WorkerDto(id=postedWorkerId.id!!, address= "ModifiedAddress")
            val putWorkerId = workersApiService.workersIdPut(modifiedWorker)
            Assertions.assertEquals(postedWorkerId, putWorkerId)
            Assertions.assertTrue(workerEquals(modifiedWorker, workersApiService.workersIdGet(putWorkerId.id!!)))
            Assertions.assertFalse(workerEquals(worker, workersApiService.workersIdGet(putWorkerId.id!!)))
        }
    }
}