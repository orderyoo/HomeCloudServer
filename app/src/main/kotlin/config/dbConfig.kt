package com.homecloud.app.config

import io.ktor.server.config.HoconApplicationConfig

fun HoconApplicationConfig.dbConfig(): DatabaseConfig {
    val dbSection = config("app.db")
    return DatabaseConfig(
        url = dbSection.property("url").getString(),
        driver = dbSection.property("driver").getString()
    )
}

data class DatabaseConfig(
    val url: String,
    val driver: String
)