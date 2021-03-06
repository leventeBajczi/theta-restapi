import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    jacoco
    id("com.github.johnrengelman.shadow") version "6.1.0"
    val kotlinVersion = "1.6.0"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

tasks{
    shadowJar {
        manifest {
            attributes(Pair("Main-Class", "hu.bme.mit.theta.restapi.Application"))
        }
    }
}


buildscript {
    repositories {
        jcenter()
        maven { url = uri("https://repo1.maven.org/maven2") }
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.2.0.M3")
    }
}

group = "hu.bme.mit.theta"
version = "1.0.0"

repositories {
    jcenter()
    maven { url = uri("https://repo1.maven.org/maven2") }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}


dependencies {
    val kotlinxCoroutinesVersion="1.6.0-RC"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.h2database:h2:1.3.148")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutinesVersion")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.apache.httpcomponents:httpclient:4.5")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutinesVersion")
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}
tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

jacoco {
    toolVersion = "0.8.7"
}

repositories {
    maven { url = uri("https://repo1.maven.org/maven2") }
    maven { url = uri("https://repo.spring.io/snapshot") }
    maven { url = uri("https://repo.spring.io/milestone") }
}
