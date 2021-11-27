package hu.bme.mit.theta.restapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.DynamicResources
import hu.bme.mit.theta.restapi.model.StaticResources
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
 * @param logicalCpu 
 * @param ramG 
 * @param ramM 
 * @param timeoutS 
 */
data class Resources(

    @field:JsonProperty("logical_cpu") val logicalCpu: Int? = null,

    @field:JsonProperty("ram_G") val ramG: Int? = null,

    @field:JsonProperty("ram_M") val ramM: Int? = null,

    @field:JsonProperty("timeout_s") val timeoutS: Int? = null
) {

}

