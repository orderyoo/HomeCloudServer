package com.homecloud.app.config

import io.ktor.server.config.HoconApplicationConfig

fun HoconApplicationConfig.serverConfig(): ServerConfig {
    return ServerConfig(
        port = property("server.port").getString().toInt(),
        host = property("server.host").getString()
    )
}

data class ServerConfig(
    val port: Int,
    val host: String
)