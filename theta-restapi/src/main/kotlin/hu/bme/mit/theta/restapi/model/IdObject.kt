package hu.bme.mit.theta.restapi.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param id 
 */
data class IdObject(

    @field:JsonProperty("id", required = true) val id: Int? = null
) {

}

