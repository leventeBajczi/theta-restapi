package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

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

}
