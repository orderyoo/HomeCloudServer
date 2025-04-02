plugins {
    kotlin("jvm")
    id("io.ktor.plugin") version "2.3.3"
}

group = "com.homecloude"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation(project(":domain"))

    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("org.jmdns:jmdns:3.5.8")
    implementation("ch.qos.logback:logback-classic:1.4.12")

    testImplementation(kotlin("test"))
}