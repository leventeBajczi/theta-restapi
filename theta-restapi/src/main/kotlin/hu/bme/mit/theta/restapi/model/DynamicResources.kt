package hu.bme.mit.theta.restapi.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param timeoutS 
 */
data class DynamicResources(

    @field:JsonProperty("timeout_s") val timeoutS: Int? = null
) {

}

