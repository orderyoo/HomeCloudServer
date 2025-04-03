plugins {
    kotlin("jvm")
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
    testImplementation(kotlin("test"))
}