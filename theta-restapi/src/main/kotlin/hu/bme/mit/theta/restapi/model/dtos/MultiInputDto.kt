package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid

/**
 *
 * @param inputs
 */
data class MultiInputDto(

    @field:Valid
    @field:JsonProperty("inputs", required = true) val inputs: List<SingleInputDto>
) {

}

