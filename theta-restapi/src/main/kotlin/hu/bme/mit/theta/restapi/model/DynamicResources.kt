package hu.bme.mit.theta.restapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
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
 * @param timeoutS 
 */
data class DynamicResources(

    @field:JsonProperty("timeout_s") val timeoutS: Int? = null
) {

}

