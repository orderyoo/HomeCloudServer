package com.homecloude.config

import io.ktor.server.config.*
import kotlin.text.toInt

class AppConfig(config: ApplicationConfig) {
    val server = ServerConfig(
        port = config.property("app.server.port").getString().toInt(),
        host = config.property("app.server.host").getString()
    )

    val mdns = MdnsConfig(
        serviceName = config.property("app.mdns.serviceName").getString(),
        serviceType = config.property("app.mdns.serviceType").getString(),
        priority = config.property("app.mdns.priority").getString().toInt(),
        weight = config.property("app.mdns.weight").getString().toInt(),
        properties = config.property("app.mdns.properties").getList()
            .associate {
                val (key, value ) = it.split('=')
                key.trim() to value.trim()
            }
    )
}

data class ServerConfig(
    val port: Int,
    val host: String
)

data class MdnsConfig(
    val serviceName: String,
    val serviceType: String,
    val priority: Int,
    val weight: Int,
    val properties: Map<String, String>
)