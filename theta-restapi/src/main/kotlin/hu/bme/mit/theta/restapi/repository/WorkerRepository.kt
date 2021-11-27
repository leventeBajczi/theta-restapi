package hu.bme.mit.theta.restapi.repository

import hu.bme.mit.theta.restapi.model.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface WorkerRepository : JpaRepository<User, Int> {

}