package com.example.chequeapp.di

import com.example.domain.network.IApiProvider
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
    fun provideGetAllUsersUseCase(api: IApiProvider): GetAllUsersUseCase =
        GetAllUsersUseCase(api)

    @Provides
    fun provideHistoryRequestUseCase(api: IApiProvider): HistoryRequestUseCase =
        HistoryRequestUseCase(api)

    @Provides
    fun provideCalculateResultUseCase(api: IApiProvider): CalculateResultUseCase =
        CalculateResultUseCase(api)

    @Provides
    fun provideRegisterUseCase(
        userRepository: IUserRepository,
        api: IApiProvider
    ): RegisterUseCase =
        RegisterUseCase(userRepository, api)

    @Provides
    fun provideLoginUseCase(
        userRepository: IUserRepository,
        api: IApiProvider
    ): LoginUseCase =
        LoginUseCase(userRepository, api)

    @Provides
    fun provideGetLoggedInUserUseCase(userRepository: IUserRepository): GetLoggedInUserUseCase =
        GetLoggedInUserUseCase(userRepository)
}