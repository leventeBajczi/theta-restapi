package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param timeoutS 
 */
data class DynamicResourcesDto(

    @field:JsonProperty("timeout_s") val timeoutS: Int? = null
) {

}

