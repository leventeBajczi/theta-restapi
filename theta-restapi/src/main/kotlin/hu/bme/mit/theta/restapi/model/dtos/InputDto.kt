package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param name 
 * @param content 
 */
data class InputDto(

    @field:JsonProperty("name", required = true) val name: String,

    @field:JsonProperty("content", required = true) val content: String? = null
) {

}

