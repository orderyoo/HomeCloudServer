package com.homecloude

import com.homecloude.config.AppConfig
import com.homecloude.routes.authRoutes
import com.homecloude.service.MDNSServiceRegistrar
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val appConfig = AppConfig(config)
    val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    embeddedServer(
        factory = Netty,
        port = appConfig.server.port,
        host = appConfig.server.host,
        module = Application::mainModule
    ).apply {
        val MDNSServiceRegistrar = MDNSServiceRegistrar(appConfig, coroutineScope)
        environment.monitor.subscribe(ApplicationStarted) {
            MDNSServiceRegistrar.registerService()
        }
        environment.monitor.subscribe(ApplicationStopped) {
            MDNSServiceRegistrar.close()
        }
    }.start(wait = true)

}

fun Application.mainModule() {
    routing {
        authRoutes()
    }
}