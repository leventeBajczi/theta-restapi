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
 * @param name 
 * @param content 
 */
data class SingleInput(

    @field:JsonProperty("name", required = true) val name: kotlin.String,

    @field:JsonProperty("content", required = true) val content: kotlin.String
) {

}

