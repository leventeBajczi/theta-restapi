package hu.bme.mit.theta.restapi.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param version 
 * @param description 
 * @param binary 
 * @param commit 
 */
data class Executable(

    @field:JsonProperty("version", required = true) val version: String,

    @field:JsonProperty("description", required = true) val description: String,

    @field:JsonProperty("binary", required = true) val binary: String,

    @field:JsonProperty("commit") val commit: String? = null
) {

}

