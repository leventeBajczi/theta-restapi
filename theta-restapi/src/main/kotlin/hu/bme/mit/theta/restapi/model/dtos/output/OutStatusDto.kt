package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty

data class OutStatusDto(
    @field:JsonProperty("usedRamMb", required = false) val usedRamMb: Double? = null,
    @field:JsonProperty("usedTimeS", required = false) val usedTimeS: Double? = null,
    @field:JsonProperty("usedCpuTimeS", required = false) val usedCpuTimeS: Double? = null,
    @field:JsonProperty("stdout", required = false) val stdout: String? = null,
    @field:JsonProperty("stderr", required = false) val stderr: String? = null
)

