package hu.bme.mit.theta.restapi.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 * 
 * @param resources 
 * @param enabled 
 */
data class TaskBenchmark(

    @field:Valid
    @field:JsonProperty("resources") val resources: Resources? = null,

    @field:JsonProperty("enabled") val enabled: Boolean? = null
) {

}

