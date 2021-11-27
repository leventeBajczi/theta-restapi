package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param logicalCpu 
 * @param ramG 
 * @param ramM 
 */
data class StaticResourcesDto(

    @field:JsonProperty("logical_cpu") val logicalCpu: Int? = null,

    @field:JsonProperty("ram_G") val ramG: Int? = null,

    @field:JsonProperty("ram_M") val ramM: Int? = null
) {

}

