package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

data class OutStatusDto(
    @field:JsonProperty("usedRamMb", required = true) val usedRamMb: Int? = null,
    @field:JsonProperty("usedTimeS", required = true) val usedTimeS: Int? = null,
    @field:JsonProperty("stdout", required = true) val stdout: String? = null,
    @field:JsonProperty("stderr", required = true) val stderr: String? = null
)

