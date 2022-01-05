package com.example.chequeapp.di

import com.example.domain.network.AbstractApiAccessObject
import com.example.domain.repository.IUserRepository
import com.example.domain.usecases.auth.GetLoggedInUserUseCase
import com.example.domain.usecases.auth.LoginUseCase
import com.example.domain.usecases.auth.RegisterUseCase
import com.example.domain.usecases.calculate.CalculateResultUseCase
import com.example.domain.usecases.history.HistoryRequestUseCase
import com.example.domain.usecases.users.GetAllUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetAllUsersUseCase(api: AbstractApiAccessObject): GetAllUsersUseCase =
        GetAllUsersUseCase(api)

    @Provides
    fun provideHistoryRequestUseCase(api: AbstractApiAccessObject): HistoryRequestUseCase =
        HistoryRequestUseCase(api)

    @Provides
    fun provideCalculateResultUseCase(api: AbstractApiAccessObject): CalculateResultUseCase =
        CalculateResultUseCase(api)

    @Provides
    fun provideRegisterUseCase(
        userRepository: IUserRepository,
        api: AbstractApiAccessObject
    ): RegisterUseCase =
        RegisterUseCase(userRepository, api)

    @Provides
    fun provideLoginUseCase(
        userRepository: IUserRepository,
        api: AbstractApiAccessObject
    ): LoginUseCase =
        LoginUseCase(userRepository, api)

    @Provides
    fun provideGetLoggedInUserUseCase(userRepository: IUserRepository): GetLoggedInUserUseCase =
        GetLoggedInUserUseCase(userRepository)
}