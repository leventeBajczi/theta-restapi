package hu.bme.mit.theta.restapi.model.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Worker(
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val id: Int,
    val name: String,
    val address: String,
) {
    
}
