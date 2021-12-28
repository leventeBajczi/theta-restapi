package hu.bme.mit.theta.restapi.model.dtos.output

import com.fasterxml.jackson.annotation.JsonProperty
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.entities.Task
import hu.bme.mit.theta.restapi.model.enums.Priority
import hu.bme.mit.theta.restapi.repository.FileRepository
import java.util.*

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
data class OutTaskDto(

    @field:JsonProperty("id", required = true) val id: Int? = null,

    @field:JsonProperty("timestamp", required = true) val timestamp: java.util.Date? = null,

    @field:JsonProperty("input", required = true) val input: MultiInputDto,

    @field:JsonProperty("userId", required = true) val userId: Int,

    @field:JsonProperty("parameters") val parameters: List<String>? = null,

    @field:JsonProperty("priority") val priority: Priority? = Priority.BESTEFFORT,

    @field:JsonProperty("benchmark") val benchmark: OutTaskBenchmarkDto? = null,

    @field:JsonProperty("doneStatus") val doneStatus: OutStatusDto? = null,

    @field:JsonProperty("toolVersion") val toolVersion: String? = null,

    @field:JsonProperty("runexecVersion") val runexecVersion: String? = null,
) {

    constructor(task: Task, fileRepository: FileRepository) : this(
        id = task.id,
        timestamp = task.timestamp,
        task.readInputs(fileRepository),
        task.userId,
        task.parameters,
        task.priority,
        OutTaskBenchmarkDto(OutResourcesDto(task.logicalCpu, ramM = task.ramMb, timeoutS = task.timeoutS)),
        doneStatus = if(task.stdout != null) OutStatusDto(
            task.usedRamMb,
            task.usedTimeS,
            task.usedCpuTimeS,
            stdout = Base64.getEncoder().encodeToString(task.stdout?.readBytes() ?: ByteArray(0)),
            stderr = Base64.getEncoder().encodeToString(task.stderr?.readBytes() ?: ByteArray(0)),
            retval = task.retval) else null,
        toolVersion = task.toolVersion,
        runexecVersion = task.runexecVersion
    ) {}

}

