package com.homecloud.app.config

import io.ktor.server.config.*

class AppConfig(config: HoconApplicationConfig) {

    val server: ServerConfig = ServerConfig(
        port = config.property("app.server.port").getString().toInt(),
        host = config.property("app.server.host").getString()
    )

    val mdns = MdnsConfig(
        serviceName = config.property("app.server.mdns.serviceName").getString(),
        serviceType = config.property("app.server.mdns.serviceType").getString(),
        priority = config.property("app.server.mdns.priority").getString().toInt(),
        weight = config.property("app.server.mdns.weight").getString().toInt(),
        properties = config.property("app.server.mdns.properties").getList()
            .associate {
                val (key, value ) = it.split('=')
                key.trim() to value.trim()
            }
    )

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
}