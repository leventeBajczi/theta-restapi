package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param logicalCpu 
 * @param ramG 
 * @param ramM 
 */
data class OutStaticResourcesDto(

    @field:JsonProperty("logical_cpu") val logicalCpu: Int? = null,

    @field:JsonProperty("ram_M") val ramM: Int? = null
)

