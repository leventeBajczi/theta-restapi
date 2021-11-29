package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.entities.Worker

/**
 * 
 * @param id 
 * @param address 
 * @param name 
 */
data class WorkerDto(

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("address", required = true) val address: String,

    @field:JsonProperty("name") val name: String? = null
) {

    constructor(worker: Worker) : this(
        id = worker.id,
        address = worker.address,
        name = worker.name
    )

}

