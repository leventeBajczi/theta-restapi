package hu.bme.mit.theta.restapi.model.entities

import hu.bme.mit.theta.restapi.model.dtos.WorkerDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Worker(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int,
    val name: String,
    val address: String,
) {
    constructor(workerDto: WorkerDto) : this(
        id = workerDto.id!!,
        address = workerDto.address,
        name = workerDto.name!!
    )
}
