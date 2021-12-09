package hu.bme.mit.theta.restapi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.io.File


@Configuration
@ConfigurationProperties(prefix = "folders")
class ApplicationConfiguration {
    var executables: String = "."
        set(value) {
            File(value).mkdirs()
            field = value
        }
    var tmp: String = "tmp"
        set(value) {
            File(value).mkdirs()
            field = value
        }
}