package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 
 * @param id 
 * @param name 
 * @param permissions 
 * @param quotas 
 */
data class UserDto(

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("name", required = true) val name: String? = null,

    @field:JsonProperty("permissions") val permissions: List<Permissions>? = null,

    @field:JsonProperty("quotas") val quotas: ResourcesDto? = null
) {

    /**
    * 
    * Values: LISTTASKS,LISTUSERS,SUBMITTASKS,MANAGEUSERS,MANAGEEXECUTABLES
    */
    enum class Permissions(val value: String) {
    
        @JsonProperty("LIST_TASKS") LISTTASKS("LIST_TASKS"),
    
        @JsonProperty("LIST_USERS") LISTUSERS("LIST_USERS"),
    
        @JsonProperty("SUBMIT_TASKS") SUBMITTASKS("SUBMIT_TASKS"),
    
        @JsonProperty("MANAGE_USERS") MANAGEUSERS("MANAGE_USERS"),
    
        @JsonProperty("MANAGE_EXECUTABLES") MANAGEEXECUTABLES("MANAGE_EXECUTABLES");
    
    }

}

