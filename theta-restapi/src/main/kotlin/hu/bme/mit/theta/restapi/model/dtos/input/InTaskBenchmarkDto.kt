package hu.bme.mit.theta.restapi.model.dtos.input

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param resources 
 * @param enabled 
 */
data class InTaskBenchmarkDto(

    @field:JsonProperty("resources") val resources: InResourcesDto? = null,

    @field:JsonProperty("enabled") val enabled: Boolean? = null
) {

}
