package hu.bme.mit.theta.restapi.integrationtest

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component

@Component
@SpringBootTest
@ConfigurationPropertiesBinding
class IntegrationTest(@Autowired val config: ApplicationConfiguration
) {
}