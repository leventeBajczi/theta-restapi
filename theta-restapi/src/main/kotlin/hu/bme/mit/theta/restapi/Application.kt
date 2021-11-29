package hu.bme.mit.theta.restapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan





@SpringBootApplication
@ComponentScan(basePackages = ["hu.bme.mit.theta.restapi", "hu.bme.mit.theta.restapi.api", "hu.bme.mit.theta.restapi.model", "hu.bme.mit.theta.restapi.repository"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}