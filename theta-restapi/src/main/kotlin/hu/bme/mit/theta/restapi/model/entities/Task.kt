package hu.bme.mit.theta.restapi.model.entities

import hu.bme.mit.theta.restapi.model.dtos.TaskDto
import org.hibernate.annotations.CreationTimestamp
import javax.persistence.*

@Entity
data class Task(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int,
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    val timestamp: java.time.OffsetDateTime,
    val inputName: String,
    val userId: Int,
    @ElementCollection
    val parameters: List<String> = emptyList(),
    val priority: TaskDto.Priority? = TaskDto.Priority.BESTEFFORT,
    val logicalCpu: Int = -1,
    val ramMb: Int = -1,
    val timeoutS: Int = -1,
) {
    
}
