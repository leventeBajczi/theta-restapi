package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param logicalCpu 
 * @param ramG 
 * @param ramM 
 * @param timeoutS 
 */
data class ResourcesDto(

    @field:JsonProperty("logical_cpu") val logicalCpu: Int? = null,

    @field:JsonProperty("ram_G") val ramG: Int? = null,

    @field:JsonProperty("ram_M") val ramM: Int? = null,

    @field:JsonProperty("timeout_s") val timeoutS: Int? = null
) {

}

