package hu.bme.mit.theta.restapi.model.dtos.inout

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 * @param inputs
 */
data class MultiInputDto(

    @field:JsonProperty("inputs", required = true) val inputs: List<SingleInputDto>
) {

}

