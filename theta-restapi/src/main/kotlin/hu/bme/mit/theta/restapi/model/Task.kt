package hu.bme.mit.theta.restapi.model

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import hu.bme.mit.theta.restapi.model.Input
import hu.bme.mit.theta.restapi.model.TaskBenchmark
import hu.bme.mit.theta.restapi.model.User
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
 * @param timestamp 
 * @param input 
 * @param user 
 * @param parameters 
 * @param priority 
 * @param benchmark 
 */
data class Task(

    @field:JsonProperty("id", required = true) val id: kotlin.Int? = null,

    @field:JsonProperty("timestamp", required = true) val timestamp: java.time.OffsetDateTime? = null,

    @field:Valid
    @field:JsonProperty("input", required = true) val input: Input,

    @field:Valid
    @field:JsonProperty("user", required = true) val user: User? = null,

    @field:JsonProperty("parameters") val parameters: kotlin.collections.List<kotlin.String>? = null,

    @field:JsonProperty("priority") val priority: Task.Priority? = Priority.bESTEFFORT,

    @field:Valid
    @field:JsonProperty("benchmark") val benchmark: TaskBenchmark? = null
) {

    /**
    * 
    * Values: bESTEFFORT,lOW,mEDIUM,hIGH
    */
    enum class Priority(val value: kotlin.String) {
    
        @JsonProperty("BEST_EFFORT") bESTEFFORT("BEST_EFFORT"),
    
        @JsonProperty("LOW") lOW("LOW"),
    
        @JsonProperty("MEDIUM") mEDIUM("MEDIUM"),
    
        @JsonProperty("HIGH") hIGH("HIGH");
    
    }

}

