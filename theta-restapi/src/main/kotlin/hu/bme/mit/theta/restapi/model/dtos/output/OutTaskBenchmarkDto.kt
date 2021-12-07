package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param resources 
 * @param enabled 
 */
data class OutTaskBenchmarkDto(

    @field:JsonProperty("resources") val resources: OutResourcesDto? = null,

    @field:JsonProperty("enabled") val enabled: Boolean? = null
) {

}

