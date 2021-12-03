package hu.bme.mit.theta.restapi.model.entities

import hu.bme.mit.theta.restapi.model.dtos.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.SingleInputDto
import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import hu.bme.mit.theta.restapi.repository.FileRepository
import org.hibernate.annotations.CreationTimestamp
import java.io.File
import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int = 0,
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val timestamp: java.util.Date = java.util.Date(),
    @ElementCollection
    val inputIds: List<Int> = emptyList(),
    @ElementCollection
    val outputIds: List<Int> = emptyList(),
    val userId: Int,
    @ElementCollection
    val parameters: List<String> = emptyList(),
    val priority: TaskDto.Priority? = TaskDto.Priority.BESTEFFORT,
    val logicalCpu: Int = -1,
    val ramMb: Int = -1,
    val timeoutS: Int = -1,
) {
    constructor(task: TaskDto, fileRepository: FileRepository) : this(
        inputIds = task.input.inputs.map {
            val noExtensionName = if(it.name.contains('.')) it.name.substring(0, it.name.lastIndexOf(".")) else it.name
            val extension = if(it.name.contains('.')) it.name.substring(it.name.lastIndexOf(".")+1) else ""
            val file = File.createTempFile(noExtensionName, extension)
            file.writeText(it.content!!)
            fileRepository.save(File(name = it.name, fullPath = file.absolutePath)).id }.toList(),
        userId = task.userId,
        parameters = task.parameters ?: emptyList(),
        priority = task.priority ?: TaskDto.Priority.BESTEFFORT,
        logicalCpu = task.benchmark?.resources?.logicalCpu ?: -1,
        ramMb = task.benchmark?.resources?.ramM ?: (task.benchmark?.resources?.ramG?.times(1000)) ?: -1,
        timeoutS = task.benchmark?.resources?.timeoutS ?: -1,
    )

    fun readInputs(fileRepository: FileRepository, includeContent: Boolean = false) : MultiInputDto = MultiInputDto(
        inputIds
            .map { fileRepository.findById(it).orElseThrow() }
            .map { SingleInputDto(name = it.name, content = if (includeContent) File(it.fullPath).readText() else null) }
            .toList())

}
