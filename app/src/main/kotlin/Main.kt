package com.homecloud.app

import com.homecloud.app.config.mdnsConfig
import com.homecloud.app.config.serverConfig
import com.homecloud.app.service.MDNSServiceRegistrar
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val serverConfig = config.serverConfig()
    val mdnsConfig = config.mdnsConfig()
    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    embeddedServer(
        factory = Netty,
        port = serverConfig.port,
        host = serverConfig.host,
        module = Application::mainModule
    ).apply {
        val serviceRegistrar = MDNSServiceRegistrar(serverConfig, mdnsConfig, coroutineScope)
        application.monitor.subscribe(ApplicationStarted) { serviceRegistrar.registerService() }
        application.monitor.subscribe(ApplicationStopped) { serviceRegistrar.close() }
    }.start(wait = true)
}