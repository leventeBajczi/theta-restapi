package hu.bme.mit.theta.restapi.model.entities

import hu.bme.mit.theta.restapi.model.dtos.UserDto
import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int,
    val name: String,
    @ElementCollection
    val permissions: List<UserDto.Permissions> = emptyList(),
    val logicalCpuQuota: Int = -1,
    val ramMbQuota: Int = -1,
    val timeshareSQuota: Int = -1,
) {

    constructor(userDto: UserDto) : this(
        id = userDto.id!!,
        name = userDto.name!!,
        permissions = userDto.permissions ?: emptyList(),
        logicalCpuQuota = userDto.quotas?.logicalCpu ?: -1,
        ramMbQuota = userDto.quotas?.ramM ?: userDto.quotas?.ramG?.times(1000) ?: -1,
        timeshareSQuota = userDto.quotas?.timeoutS ?: -1,
    ){}
    
}
