package com.homecloude.service

import com.homecloude.config.AppConfig
import io.ktor.utils.io.core.Closeable
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

class LocalService(
    private val appConfig: AppConfig,
    //private val logger: Logger
) : Closeable {
    private var jmdns: JmDNS? = null

    fun registerService() {
        try {
            jmdns = JmDNS.create(
                InetAddress.getByName(appConfig.server.host),
                appConfig.mdns.serviceName
            ).apply {
                registerService(
                    ServiceInfo.create(
                        appConfig.mdns.serviceType,
                        appConfig.mdns.serviceName,
                        appConfig.server.port,
                        0,
                        0,
                        mapOf("version" to "1.0")
                    )
                )
            }
            println("mDNS service registered successfully")
            //logger.info("mDNS service registered successfully")
        } catch (e: Exception) {
            //logger.error("mDNS registration failed", e)
            println("mDNS registration failed: ${e.message}")
        }
    }

    override fun close() {
        jmdns?.close()
        println("mDNS service unregistered")
        //logger.info("mDNS service unregistered")
    }
}