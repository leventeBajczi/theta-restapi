package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.resources.ResourcesApiService
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component

@Component
@SpringBootTest
@ConfigurationPropertiesBinding
class ResourcesTest(@Autowired val resourcesService: ResourcesApiService) {
    @Test
    fun testGet() {
        runBlocking {
            val resources = resourcesService.resourcesGet()
            Assertions.assertNotNull(resources.logicalCpu)
            Assertions.assertTrue(resources.ramG != null || resources.ramM != null)
        }
    }
}