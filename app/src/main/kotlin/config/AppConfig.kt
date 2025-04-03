package com.homecloude.config

import io.ktor.server.config.*
import kotlin.text.toInt

class AppConfig(config: ApplicationConfig) {
    val server = ServerConfig(
        port = config.property("ktor.deployment.port").getString().toInt(),
        host = config.property("ktor.deployment.host").getString()
    )

    val mdns = MdnsConfig(
        serviceName = config.property("ktor.mdns.serviceName").getString(),
        serviceType = config.property("ktor.mdns.serviceType").getString()
    )
}

data class ServerConfig(val port: Int, val host: String)
data class MdnsConfig(val serviceName: String, val serviceType: String)