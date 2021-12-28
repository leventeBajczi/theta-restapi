package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param version 
 * @param description 
 * @param commit
 */
data class OutExecutableDto(

    @field:JsonProperty("version", required = true) val version: String,

    @field:JsonProperty("relativePath", required = true) val relativePath: String,
) {

}

