package hu.bme.mit.theta.restapi.model.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.entities.Task
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
data class TaskDto(

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("timestamp", required = true) val timestamp: java.time.OffsetDateTime? = null,

    @field:Valid
    @field:JsonProperty("input", required = true) val input: InputDto,

    @field:Valid
    @field:JsonProperty("user", required = true) val user: UserDto? = null,

    @field:JsonProperty("parameters") val parameters: List<String>? = null,

    @field:JsonProperty("priority") val priority: Priority? = Priority.BESTEFFORT,

    @field:Valid
    @field:JsonProperty("benchmark") val benchmark: TaskBenchmarkDto? = null
) {

    constructor(task: Task) : this(
        id = task.id,
        timestamp = task.timestamp,
        InputDto(task.inputName),
        UserDto(id = task.userId),
        task.parameters,
        task.priority,
        TaskBenchmarkDto(ResourcesDto(task.logicalCpu, ramM = task.ramMb, timeoutS = task.timeoutS))
    ) {}

    /**
    * 
    * Values: BESTEFFORT,LOW,MEDIUM,HIGH
    */
    enum class Priority(val value: String) {
    
        @JsonProperty("BEST_EFFORT") BESTEFFORT("BEST_EFFORT"),
    
        @JsonProperty("LOW") LOW("LOW"),
    
        @JsonProperty("MEDIUM") MEDIUM("MEDIUM"),
    
        @JsonProperty("HIGH") HIGH("HIGH");
    
    }

}

