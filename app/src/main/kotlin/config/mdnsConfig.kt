package com.homecloud.app.config

import io.ktor.server.config.HoconApplicationConfig

fun HoconApplicationConfig.mdnsConfig(): MdnsConfig {
    val mdnsSection = config("mdns")
    return MdnsConfig(
        serviceName = mdnsSection.property("serviceName").getString(),
        serviceType = mdnsSection.property("serviceType").getString(),
        priority = mdnsSection.property("priority").getString().toInt(),
        weight = mdnsSection.property("weight").getString().toInt(),
        properties = mdnsSection.property("properties").getList()
            .associate {
                val (key, value) = it.split('=')
                key.trim() to value.trim()
            }
    )
}

data class MdnsConfig(
    val serviceName: String,
    val serviceType: String,
    val priority: Int,
    val weight: Int,
    val properties: Map<String, String>
)