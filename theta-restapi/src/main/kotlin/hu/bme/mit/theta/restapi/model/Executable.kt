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
 * @param version 
 * @param description 
 * @param binary 
 * @param commit 
 */
data class Executable(

    @field:JsonProperty("version", required = true) val version: kotlin.String,

    @field:JsonProperty("description", required = true) val description: kotlin.String,

    @field:JsonProperty("binary", required = true) val binary: kotlin.String,

    @field:JsonProperty("commit") val commit: kotlin.String? = null
) {

}

