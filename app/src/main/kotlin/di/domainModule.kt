package com.homecloud.app.di

import org.koin.dsl.module
import usecases.ApiKeyVerification
import usecases.file.FileDeleteUseCase
import usecases.file.FileEditUseCase
import usecases.file.FileGetUseCase
import usecases.file.FileSaveUseCase
import usecases.space.SpaceCreateUseCase
import usecases.space.SpaceDeleteUseCase
import usecases.space.SpaceEditUseCase
import usecases.space.SpacesGetUseCase
import usecases.user.UserDeleteUseCase
import usecases.user.UserEditUseCase
import usecases.user.UserGetByIdUseCase
import usecases.user.UserLoginUseCase
import usecases.user.UserRegisterUseCase
import usecases.user.UsersGetBySpaceIdUseCase

val domainModule = module {

    single<ApiKeyVerification> { ApiKeyVerification(get()) }

    single<UserRegisterUseCase> { UserRegisterUseCase(get(), get(), get()) }
    single<UserLoginUseCase> { UserLoginUseCase(get(), get(), get()) }
    single<UserEditUseCase> { UserEditUseCase(get(), get()) }
    single<UserDeleteUseCase> { UserDeleteUseCase(get()) }
    single<UserGetByIdUseCase> { UserGetByIdUseCase(get()) }
    single<UsersGetBySpaceIdUseCase> { UsersGetBySpaceIdUseCase(get()) }

    single<SpaceCreateUseCase> { SpaceCreateUseCase(get()) }
    single<SpaceEditUseCase> { SpaceEditUseCase(get()) }
    single<SpaceDeleteUseCase> { SpaceDeleteUseCase(get()) }
    single<SpacesGetUseCase> { SpacesGetUseCase(get()) }

    single<FileSaveUseCase> { FileSaveUseCase(get(), get()) }
    single<FileEditUseCase> { FileEditUseCase(get(), get()) }
    single<FileDeleteUseCase> { FileDeleteUseCase(get(), get()) }
    single<FileGetUseCase> { FileGetUseCase(get(), get()) }

}