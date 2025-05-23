plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

application {
    mainClass = "com.homecloud.app.MainKt"
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.koin.core)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger)
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)
    implementation(libs.kotlinxSerialization)
    implementation(libs.jmdns)
}