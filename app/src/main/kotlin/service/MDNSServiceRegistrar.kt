package com.homecloud.app.service

import com.homecloud.app.config.AppConfig
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

class MDNSServiceRegistrar(
    private val appConfig: AppConfig,
    private val coroutineScope: CoroutineScope
) : Closeable {
    private var jmdns: JmDNS? = null

    fun registerService() = coroutineScope.launch(Dispatchers.IO) {
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
                        appConfig.mdns.weight,
                        appConfig.mdns.priority,
                        appConfig.mdns.properties
                    )
                )
            }
            println("mDNS service registered successfully")
        } catch (e: Exception) {
            println("mDNS registration failed: ${e.message}")
            close()
        }
    }

    override fun close() {
        runCatching {
            jmdns?.unregisterAllServices()
            jmdns?.close()
            println("mDNS service unregistered")
        }.onFailure {
            println("mDNS error closing")
        }
    }
}