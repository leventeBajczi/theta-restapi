package hu.bme.mit.theta.restapi.model.dtos.input

import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.enums.Permissions

/**
 * 
 * @param name
 * @param permissions 
 * @param quotas 
 */
data class InUserDto  (

    @field:JsonProperty("name", required = true) val name: String? = null,

    @field:JsonProperty("permissions") val permissions: List<Permissions>? = null,

    @field:JsonProperty("quotas") val quotas: InResourcesDto? = null
) {}

