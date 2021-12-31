package hu.bme.mit.theta.restapi.model.entities

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.model.dtos.inout.MultiInputDto
import hu.bme.mit.theta.restapi.model.dtos.inout.SingleInputDto
import hu.bme.mit.theta.restapi.model.dtos.input.InTaskDto
import hu.bme.mit.theta.restapi.model.enums.Priority
import hu.bme.mit.theta.restapi.repository.FileRepository
import org.hibernate.annotations.CreationTimestamp
import java.io.File
import java.util.*
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
    val inputIds: MutableList<Int> = arrayListOf(),
    @ElementCollection
    val outputIds: MutableList<Int> = arrayListOf(),
    val userId: Int,
    @ElementCollection
    val parameters: MutableList<String> = arrayListOf(),
    val toolVersion: String? = null,
    val runexecVersion: String? = null,
    val priority: Priority? = Priority.BESTEFFORT,
    val logicalCpu: Int = -1,
    val ramMb: Int = -1,
    val timeoutS: Int = -1,
    val useRunexec: Boolean = false,
    val useScheduling: Boolean = false,
    val usedRamMb: Double? = null,
    val usedTimeS: Double? = null,
    val usedCpuTimeS: Double? = null,
    val stdout: File? = null,
    val stderr: File? = null,
    val retval: Int? = null,
    val terminationreason: String? = null
) {
    constructor(task: InTaskDto, fileRepository: FileRepository, config: ApplicationConfiguration) : this(
        inputIds = task.input.inputs.map {
            val noExtensionName = if(it.name.contains('.')) it.name.substring(0, it.name.lastIndexOf(".")) else it.name
            val extension = if(it.name.contains('.')) it.name.substring(it.name.lastIndexOf(".")+1) else ""
            val file = File.createTempFile(noExtensionName, extension, File(config.tmp))
            file.writeBytes(Base64.getDecoder().decode(it.content!!))
            fileRepository.save(File(name = it.name, fullPath = file.absolutePath)).id }.toMutableList(),
        userId = task.userId,
        parameters = task.parameters?.toMutableList() ?: arrayListOf(),
        priority = task.priority ?: Priority.BESTEFFORT,
        logicalCpu = task.benchmark?.resources?.logicalCpu ?: -1,
        ramMb = task.benchmark?.resources?.ramM ?: (task.benchmark?.resources?.ramG?.times(1000)) ?: -1,
        timeoutS = task.benchmark?.resources?.timeoutS ?: -1,
        useRunexec = task.benchmark?.useRunexec ?: false,
        useScheduling = task.benchmark?.useScheduling ?: false,
    )

    fun readInputs(fileRepository: FileRepository, includeContent: Boolean = false) : MultiInputDto = MultiInputDto(
        inputIds
            .map { fileRepository.findById(it).orElseThrow() }
            .map { SingleInputDto(name = it.name, content = if (includeContent) Base64.getEncoder().encodeToString(File(it.fullPath).readBytes()) else null) }
            .toList())

}
