package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param resources 
 * @param enabled 
 */
data class TaskBenchmarkDto(

    @field:Valid
    @field:JsonProperty("resources") val resources: ResourcesDto? = null,

    @field:JsonProperty("enabled") val enabled: Boolean? = null
) {

}

