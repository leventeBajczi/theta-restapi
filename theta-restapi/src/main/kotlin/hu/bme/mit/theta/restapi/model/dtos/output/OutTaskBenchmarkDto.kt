package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param resources 
 * @param useRunexec
 * @param useScheduling
 */
data class OutTaskBenchmarkDto(

    @field:JsonProperty("resources") val resources: OutResourcesDto? = null,

    @field:JsonProperty("useRunexec") val useRunexec: Boolean? = null,

    @field:JsonProperty("useScheduling") val useScheduling: Boolean? = null
) {

}

