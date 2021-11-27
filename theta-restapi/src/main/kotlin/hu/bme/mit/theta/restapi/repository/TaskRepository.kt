package hu.bme.mit.theta.restapi.repository

import hu.bme.mit.theta.restapi.model.entities.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Int> {

}