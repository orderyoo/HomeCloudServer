package com.homecloude

import com.homecloude.config.AppConfig
import com.homecloude.routes.authRoutes
import com.homecloude.service.LocalService
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val appConfig = AppConfig(config)

    embeddedServer(
        factory = Netty,
        port = appConfig.server.port,
        host = appConfig.server.host,
        module = Application::mainModule
    ).apply {
        val localService = LocalService(appConfig)
        environment.monitor.subscribe(ApplicationStarted) {
            localService.registerService()
        }
        environment.monitor.subscribe(ApplicationStopped) {
            localService.close()
        }
    }.start(wait = true)

}

fun Application.mainModule() {
    routing {
        authRoutes()
    }
}