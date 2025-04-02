package com.homecloude
import com.homecloude.config.AppConfig
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val appConfig = AppConfig(config)

    var jmdns: JmDNS? = null

    val server = embeddedServer(
        factory = Netty,
        port = appConfig.server.port,
        host = appConfig.server.host,
        module = Application::mainModule
    )

    server.environment.monitor.subscribe(ApplicationStarted) {
        try {
            jmdns = JmDNS.create(
                InetAddress.getByName(appConfig.server.host),
                "HomeCloud"
            ).apply {
                registerService(
                    ServiceInfo.create(
                        "_http._tcp.local.",
                        "HomeCloudServer",
                        appConfig.server.port,
                        0,
                        0,
                        mapOf("version" to "1.0")
                    )
                )
                println("mDNS service registered successfully")
            }
        } catch (e: Exception) {
            println("mDNS registration failed: ${e.message}")
        }
    }

    server.environment.monitor.subscribe(ApplicationStopped) {
        jmdns?.close()
        println("mDNS service unregistered")
    }

    server.start(wait = true)
}

fun Application.mainModule() {

    routing {
        get("/version") {
            println("A")
        }
    }
}