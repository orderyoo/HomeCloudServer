package com.homecloud.app.di

import com.homecloud.app.config.MdnsConfig
import com.homecloud.app.config.ServerConfig
import com.homecloud.app.config.dbConfig
import com.homecloud.app.config.mdnsConfig
import com.homecloud.app.config.serverConfig
import com.homecloud.app.service.MDNSServiceRegistrar
import com.typesafe.config.ConfigFactory
import db.DatabaseConfig
import io.ktor.server.config.HoconApplicationConfig
import org.koin.dsl.module

val presentationModule = module {

    single<HoconApplicationConfig> { HoconApplicationConfig(ConfigFactory.load()) }
    factory<ServerConfig> { get<HoconApplicationConfig>().serverConfig() }
    factory<MdnsConfig> { get<HoconApplicationConfig>().mdnsConfig() }
    factory<DatabaseConfig> { get<HoconApplicationConfig>().dbConfig() }

    single {
        MDNSServiceRegistrar(
            serverConfig = get(),
            mdnsConfig = get()
        )
    }

}