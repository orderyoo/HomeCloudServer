package com.homecloud.app.service

import com.homecloud.app.config.MdnsConfig
import com.homecloud.app.config.ServerConfig
import io.ktor.utils.io.core.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

class MDNSServiceRegistrar(
    private val serverConfig: ServerConfig,
    private val mdnsConfig: MdnsConfig,
) : Closeable {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var jmdns: JmDNS? = null

    fun registerService() = scope.launch(Dispatchers.IO) {
        try {
            val hostAddress = InetAddress.getByName(serverConfig.host)
            jmdns = JmDNS.create(hostAddress, mdnsConfig.serviceName).apply {
                registerService(
                    ServiceInfo.create(
                        mdnsConfig.serviceType,
                        mdnsConfig.serviceName,
                        serverConfig.port,
                        mdnsConfig.weight,
                        mdnsConfig.priority,
                        mdnsConfig.properties
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
        scope.cancel()
        runCatching {
            jmdns?.unregisterAllServices()
            jmdns?.close()
            println("mDNS service unregistered")
        }.onFailure {
            println("mDNS error closing")
        }
    }
}