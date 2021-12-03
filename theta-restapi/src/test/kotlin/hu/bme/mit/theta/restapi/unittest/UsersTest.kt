package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.api.users.UsersApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.model.dtos.UserDto
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import javax.transaction.Transactional
import kotlin.reflect.full.memberProperties

@Component
@SpringBootTest
@Transactional
@ConfigurationPropertiesBinding
class UsersTest(@Autowired val usersApiService: UsersApiService) {
    private fun userEquals(user1: UserDto, user2: UserDto) : Boolean {
        for(prop in UserDto::class.memberProperties) {
            val user1Prop = prop.get(user1)
            val user2Prop = prop.get(user2)
            if(user1Prop != null && user2Prop != null && user1Prop != user2Prop)
                return false
        }
        return true
    }


    @Test
    fun testEmptyGet() {
        runBlocking {
            val tasks = usersApiService.usersGet()
            Assertions.assertEquals(emptyList<UserDto>(), tasks)
        }
    }

    @Test
    fun testPost() {
        runBlocking {
            val user = UserDto(name="Name")
            val preUsers = usersApiService.usersGet()
            Assertions.assertFalse(preUsers.any { userEquals(it, user) })
            usersApiService.usersPost(user)
            val postUsers = usersApiService.usersGet()
            Assertions.assertTrue(postUsers.any { userEquals(it, user) })
        }
    }

    @Test
    fun testGetById() {
        runBlocking {
            val user = UserDto(name="Name")
            val postedUserId = usersApiService.usersPost(user)
            Assertions.assertTrue(userEquals(user, usersApiService.usersIdGet(postedUserId.id!!)))
        }
    }

    @Test
    fun testDelete() {
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                val user = UserDto(name="Name")
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
            val user = UserDto(name="Name")
            val postedUserId = usersApiService.usersPost(user)
            Assertions.assertTrue(userEquals(user, usersApiService.usersIdGet(postedUserId.id!!)))
            val modifiedUser = UserDto(id=postedUserId.id!!, name="ModifiedName")
            val putUserId = usersApiService.usersIdPut(modifiedUser)
            Assertions.assertEquals(postedUserId, putUserId)
            Assertions.assertTrue(userEquals(modifiedUser, usersApiService.usersIdGet(putUserId.id!!)))
            Assertions.assertFalse(userEquals(user, usersApiService.usersIdGet(putUserId.id!!)))
        }
    }
}