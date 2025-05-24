package com.homecloud.app.di

import db.DatabaseFactory
import org.koin.dsl.module
import repositories.ApiKeyRepository
import repositories.ApiKeyRepositoryImpl
import repositories.FileMetadataRepository
import repositories.FileMetadataRepositoryImpl
import repositories.FileRepository
import repositories.FileRepositoryImpl
import repositories.SpaceRepository
import repositories.SpaceRepositoryImpl
import repositories.UserRepository
import repositories.UserRepositoryImpl
import utils.HashService

val dataModule = module {
    single {
        DatabaseFactory.init(get())
        DatabaseFactory.createSchemaIfNotExists()
    }

    single<ApiKeyRepository> { ApiKeyRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
    single<SpaceRepository> { SpaceRepositoryImpl() }
    single<FileMetadataRepository> { FileMetadataRepositoryImpl() }
    single<FileRepository> { FileRepositoryImpl() }

//    single<HashService> {  }
}