package com.homecloud.app

import com.homecloud.app.config.ServerConfig
import com.homecloud.app.di.dataModule
import com.homecloud.app.di.domainModule
import com.homecloud.app.di.presentationModule
import com.homecloud.app.service.MDNSServiceRegistrar
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.GlobalContext.startKoin
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main() {
    val koinApp = startKoin {
        modules(domainModule, dataModule, presentationModule)
        slf4jLogger()
    }.koin

    embeddedServer(
        factory = Netty,
        port = koinApp.get<ServerConfig>().port,
        host = koinApp.get<ServerConfig>().host,
        module = {
            install(Koin)
            mainModule()
        }
    ).apply {
        application.monitor.subscribe(ApplicationStarted) {
            koinApp.get<MDNSServiceRegistrar>().registerService()
        }
        application.monitor.subscribe(ApplicationStopped) {
            koinApp.get<MDNSServiceRegistrar>().close()
            koinApp.close()
        }
    }.start(wait = true)
}