package hu.bme.mit.theta.restapi.unittest

import hu.bme.mit.theta.restapi.ApplicationConfiguration
import hu.bme.mit.theta.restapi.api.executables.ExecutablesApiService
import hu.bme.mit.theta.restapi.exceptions.NoSuchElement
import hu.bme.mit.theta.restapi.membersEqual
import hu.bme.mit.theta.restapi.model.dtos.input.InExecutableDto
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import java.nio.file.Path
import kotlin.io.path.absolutePathString

@Component
@SpringBootTest
@ConfigurationPropertiesBinding
class ExecutableTest(
    @Autowired val executablesApiService: ExecutablesApiService,
    @Autowired val config: ApplicationConfiguration) {

    @Test
    fun testEmptyGet(@TempDir tempDir1: Path, @TempDir tempDir2: Path) {
        val executablesSave = config.executables
        val tmpSave = config.tmp

        config.executables = tempDir1.absolutePathString()
        config.tmp = tempDir2.absolutePathString()
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                 executablesApiService.thetaGet()
            }
        }
        Assertions.assertThrows(NoSuchElement::class.java) {
            runBlocking {
                 executablesApiService.runexecGet()
            }
        }

        config.tmp = tmpSave
        config.executables = executablesSave
    }
    @Test
    fun testPut(@TempDir tempDir1: Path, @TempDir tempDir2: Path) {
        val executablesSave = config.executables
        val tmpSave = config.tmp

        config.executables = tempDir1.absolutePathString()
        config.tmp = tempDir2.absolutePathString()
        val executable = InExecutableDto(
            version = "SampleVersion",
            description = "SampleDescription",
            binaryBytes = "BinaryContent".toByteArray(),
            commit = "SampleCommit",
            relativePath = "RelativePath"
        )
        val executableNoBinary = InExecutableDto(
            version = "SampleVersion",
            description = "SampleDescription",
            commit = "SampleCommit",
            relativePath = "RelativePath"
        )
        runBlocking {
            executablesApiService.thetaPut(executable)
            Assertions.assertTrue(membersEqual(executableNoBinary, executablesApiService.thetaGet()), Pair(executableNoBinary, executablesApiService.thetaGet()).toString())
        }
        runBlocking {
            executablesApiService.runexecPut(executable)
            Assertions.assertTrue(membersEqual(executableNoBinary, executablesApiService.runexecGet()), Pair(executableNoBinary, executablesApiService.runexecGet()).toString())
        }

        config.tmp = tmpSave
        config.executables = executablesSave
    }
}