package hu.bme.mit.theta.restapi.model.dtos.input

import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.enums.Priority

/**
 * 
 * @param input
 * @param userId
 * @param parameters 
 * @param priority 
 * @param benchmark 
 */
data class InTaskDto(

    @field:JsonProperty("input", required = true) val input: MultiInputDto,

    @field:JsonProperty("userId", required = true) val userId: Int,

    @field:JsonProperty("parameters") val parameters: List<String>? = null,

    @field:JsonProperty("priority") val priority: Priority? = Priority.BESTEFFORT,

    @field:JsonProperty("benchmark") val benchmark: InTaskBenchmarkDto? = null
) {


}

