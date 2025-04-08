plugins {
    kotlin("jvm") version "2.0.20"
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

    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    testImplementation(kotlin("test"))
}