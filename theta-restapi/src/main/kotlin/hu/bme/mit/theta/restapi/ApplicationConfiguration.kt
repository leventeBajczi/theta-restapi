package hu.bme.mit.theta.restapi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "folders")
class ApplicationConfiguration {
    var executables: String = "."
}