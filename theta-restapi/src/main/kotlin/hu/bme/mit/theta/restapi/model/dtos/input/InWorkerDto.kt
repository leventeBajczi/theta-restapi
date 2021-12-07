package hu.bme.mit.theta.restapi.model.dtos.input

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param address
 * @param name
 */
data class InWorkerDto(

    @field:JsonProperty("address", required = true) val address: String,

    @field:JsonProperty("name") val name: String? = null
) {
}

