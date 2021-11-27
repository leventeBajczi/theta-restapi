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
 * @param id 
 * @param address 
 * @param name 
 */
data class Worker(

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("address", required = true) val address: String,

    @field:JsonProperty("name") val name: String? = null
) {

}

