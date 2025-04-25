plugins {
    kotlin("jvm")
}

group = "com.homecloud"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)
    implementation(libs.sqlite.jdbc)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}