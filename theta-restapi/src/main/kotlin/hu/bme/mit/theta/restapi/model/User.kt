package hu.bme.mit.theta.restapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import hu.bme.mit.theta.restapi.model.Resources
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

/**
 * 
 * @param id 
 * @param name 
 * @param permissions 
 * @param quotas 
 */
data class User(

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("name", required = true) val name: String,

    @field:JsonProperty("permissions") val permissions: List<Permissions>? = null,

    @field:Valid
    @field:JsonProperty("quotas") val quotas: Resources? = null
) {

    /**
    * 
    * Values: lISTTASKS,lISTUSERS,sUBMITTASKS,mANAGEUSERS,mANAGEEXECUTABLES
    */
    enum class Permissions(val value: String) {
    
        @JsonProperty("LIST_TASKS") lISTTASKS("LIST_TASKS"),
    
        @JsonProperty("LIST_USERS") lISTUSERS("LIST_USERS"),
    
        @JsonProperty("SUBMIT_TASKS") sUBMITTASKS("SUBMIT_TASKS"),
    
        @JsonProperty("MANAGE_USERS") mANAGEUSERS("MANAGE_USERS"),
    
        @JsonProperty("MANAGE_EXECUTABLES") mANAGEEXECUTABLES("MANAGE_EXECUTABLES");
    
    }

}

