package hu.bme.mit.theta.restapi.integrationtest

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.model.dtos.inout.IdObjectDto
import hu.bme.mit.theta.restapi.model.dtos.output.OutTaskDto
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.nio.file.Path
import java.util.*
import kotlin.io.path.absolutePathString

@SpringBootTest
@AutoConfigureMockMvc
@ConfigurationPropertiesBinding
class IntegrationTest(
    @Autowired val config: ApplicationConfiguration,
    @Autowired val mockMvc: MockMvc
) {

    @Test
    fun testDirectTheta(@TempDir test: Path) {
        config.executables = test.absolutePathString()
        val thetaZipBytes = this::class.java.getResource("/theta.zip").readBytes()
        mockMvc.perform(multipart("/theta")
            .file("binary", thetaZipBytes)
            .param("commit", "SampleCommit")
            .param("version", "SampleVersion")
            .param("relativePath", "theta/theta-start.sh")
            .param("description", "SampleDescription").with { request -> request.method = "PUT"; request }
        ).andExpect(status().isOk)

        val inputContent = Base64.getEncoder().encodeToString(this::class.java.getResource("/input.c").readBytes())

        var id: Int? = null

        mockMvc.perform(post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""{"input":{"inputs":[{"name":"inputfile","content":"$inputContent"}]},"userId":0,"parameters":["inputfile","--portfolio","COMPLEX"]}""")
        ).andExpect(status().isOk).andDo {
            id =((it.asyncResult as ResponseEntity<*>).body as IdObjectDto).id
        }
        Assertions.assertNotNull(id)

        var done = false
        var counter = 10
        while(!done && counter > 0) {
            mockMvc.perform(get("/tasks/$id")).andDo {
                val map =((it.asyncResult as ResponseEntity<*>).body as OutTaskDto?)
                if (map?.doneStatus != null) {
                    done = true
                    Assertions.assertTrue(map.doneStatus?.stdout!!.contains("SafetyResult Unsafe"))
                } else {
                    counter--
                }
            }
            Thread.sleep(500)
        }
        Assertions.assertTrue(counter > 0, "Task did not finish in the given timeframe.")
    }
}