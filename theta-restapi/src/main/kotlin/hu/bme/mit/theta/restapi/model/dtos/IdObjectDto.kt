package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param id 
 */
data class IdObjectDto(

    @field:JsonProperty("id", required = true) val id: Int? = null
) {

}

