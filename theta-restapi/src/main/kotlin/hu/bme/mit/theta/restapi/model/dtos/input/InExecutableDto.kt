package hu.bme.mit.theta.restapi.model.dtos.input

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param version 
 * @param description 
 * @param binaryBytes
 * @param commit
 */
data class InExecutableDto(

    @field:JsonProperty("version", required = true) val version: String,

    @field:JsonProperty("description", required = true) val description: String,

    @field:JsonProperty("binaryBytes", required = true) val binaryBytes: ByteArray? = null,

    @field:JsonProperty("commit") val commit: String? = null
) {

}
