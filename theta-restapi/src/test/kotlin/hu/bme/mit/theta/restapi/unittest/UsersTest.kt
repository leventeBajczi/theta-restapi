package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.users.UsersApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.membersEqual
import hu.bme.mit.theta.restapi.model.dtos.input.InUserDto
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@SpringBootTest
@Transactional
@ConfigurationPropertiesBinding
class UsersTest(@Autowired val usersApiService: UsersApiService) {
    @Test
    fun testEmptyGet() {
        runBlocking {
            val tasks = usersApiService.usersGet()
            Assertions.assertEquals(emptyList<InUserDto>(), tasks)
        }
    }

    @Test
    fun testPost() {
        runBlocking {
            val user = InUserDto(name="Name")
            val preUsers = usersApiService.usersGet()
            Assertions.assertFalse(preUsers.any { membersEqual(it, user) })
            usersApiService.usersPost(user)
            val postUsers = usersApiService.usersGet()
            Assertions.assertTrue(postUsers.any { membersEqual(it, user) })
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            val user = InUserDto(name="Name")
            val postedUserId = usersApiService.usersPost(user)
            Assertions.assertTrue(membersEqual(usersApiService.usersIdGet(postedUserId.id!!), user))
        }
    }

    @Test
    fun testDelete() {
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                val user = InUserDto(name="Name")
                val postedUserId = usersApiService.usersPost(user)
                val deletedUserId = usersApiService.usersIdDelete(postedUserId.id!!)
                Assertions.assertEquals(postedUserId, deletedUserId)
                usersApiService.usersIdGet(deletedUserId.id!!)
            }
        }
    }

    @Test
    fun testPut() {
        runBlocking {
            val user = InUserDto(name="Name")
            val postedUserId = usersApiService.usersPost(user)
            Assertions.assertTrue(membersEqual(usersApiService.usersIdGet(postedUserId.id!!), user))
            val modifiedUser = InUserDto(name="ModifiedName")
            val putUserId = usersApiService.usersIdPut(modifiedUser, postedUserId.id!!)
            Assertions.assertEquals(postedUserId, putUserId)
            Assertions.assertTrue(membersEqual(usersApiService.usersIdGet(putUserId.id!!), modifiedUser))
            Assertions.assertFalse(membersEqual(usersApiService.usersIdGet(putUserId.id!!), user))
        }
    }
}