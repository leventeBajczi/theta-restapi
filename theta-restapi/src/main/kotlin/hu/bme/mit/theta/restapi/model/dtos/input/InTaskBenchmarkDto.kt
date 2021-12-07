package hu.bme.mit.theta.restapi.model.dtos.input

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param resources 
 * @param useRunexec
 */
data class InTaskBenchmarkDto(

    @field:JsonProperty("resources") val resources: InResourcesDto? = null,

    @field:JsonProperty("useRunexec") val useRunexec: Boolean? = null
) {

}

