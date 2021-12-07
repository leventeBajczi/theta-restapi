package hu.bme.mit.theta.restapi.model.entities

import hu.bme.mit.theta.restapi.model.dtos.input.InWorkerDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Worker(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int = 0,
    val name: String?,
    val address: String,
) {
    constructor(workerDto: InWorkerDto, id: Int = 0) : this(
        id = id,
        address = workerDto.address,
        name = workerDto.name
    )
}
