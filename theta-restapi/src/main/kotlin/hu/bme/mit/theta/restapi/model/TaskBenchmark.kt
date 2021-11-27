package hu.bme.mit.theta.restapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.Resources
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
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

