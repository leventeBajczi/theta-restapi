package hu.bme.mit.theta.restapi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import java.io.File


@Configuration
@ConfigurationProperties(prefix = "folders")
class ApplicationConfiguration {
    var executables: String = "executables"
        set(value) {
            File(value).mkdirs()
            field = value
        }
    var tmp: String = "tmp"
        set(value) {
            File(value).mkdirs()
            field = value
        }

    // this is necessary because above setters are not run otherwise:
    // https://discuss.kotlinlang.org/t/why-is-the-setter-of-a-property-not-called-on-construction/14488/6
    init {
        executables = executables
        tmp = executables
    }
}