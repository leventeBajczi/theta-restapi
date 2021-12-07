package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.entities.User
import hu.bme.mit.theta.restapi.model.enums.Permissions

/**
 * 
 * @param id 
 * @param name 
 * @param permissions 
 * @param quotas 
 */
data class OutUserDto  (

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("name", required = true) val name: String? = null,

    @field:JsonProperty("permissions") val permissions: List<Permissions>? = null,

    @field:JsonProperty("quotas") val quotas: OutResourcesDto? = null
) {

    constructor(user: User) : this(
        id = user.id,
        name = user.name,
        permissions = user.permissions,
        quotas = OutResourcesDto(
            logicalCpu = user.logicalCpuQuota,
            ramM = user.ramMbQuota,
            timeoutS = user.timeshareSQuota)
    ) {}

}

