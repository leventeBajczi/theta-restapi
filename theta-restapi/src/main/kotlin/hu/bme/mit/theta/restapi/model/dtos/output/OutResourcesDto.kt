package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param logicalCpu 
 * @param ramM
 * @param timeoutS 
 */
data class OutResourcesDto(

    @field:JsonProperty("logical_cpu") val logicalCpu: Int? = null,

    @field:JsonProperty("ram_M") val ramM: Int? = null,

    @field:JsonProperty("timeout_s") val timeoutS: Int? = null
) {

}

