package hu.bme.mit.theta.restapi.repository

import hu.bme.mit.theta.restapi.model.entities.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Int> {

}