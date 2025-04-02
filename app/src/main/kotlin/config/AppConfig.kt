package com.homecloude.config

import io.ktor.server.config.*
import kotlin.text.toInt

data class ServerConfig(
    val port: Int,
    val host: String
)

class AppConfig(config: ApplicationConfig) {
    val server = ServerConfig(
        port = config.property("ktor.deployment.port").getString().toInt(),
        host = config.property("ktor.deployment.host").getString()
    )
}