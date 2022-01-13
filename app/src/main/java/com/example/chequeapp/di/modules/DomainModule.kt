package com.example.chequeapp.di.modules

import com.example.domain.repository.IEventsRepository
import com.example.network.IApiProvider
import com.example.domain.repository.IUserRepository
import com.example.domain.usecases.auth.GetLoggedInUserUseCase
import com.example.domain.usecases.auth.LoginUseCase
import com.example.domain.usecases.auth.RegisterUseCase
import com.example.domain.usecases.calculate.CalculationUseCase
import com.example.domain.usecases.history.HistoryRequestUseCase
import com.example.domain.usecases.users.GetAllUsersUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetAllUsersUseCase(userRepository: IUserRepository): GetAllUsersUseCase =
        GetAllUsersUseCase(userRepository)

    @Provides
    fun provideHistoryRequestUseCase(eventsRepository: IEventsRepository): HistoryRequestUseCase =
        HistoryRequestUseCase(eventsRepository)

    @Provides
    fun provideCalculationUseCase(eventsRepository: IEventsRepository): CalculationUseCase =
        CalculationUseCase(eventsRepository)

    @Provides
    fun provideRegisterUseCase(userRepository: IUserRepository): RegisterUseCase =
        RegisterUseCase(userRepository)

    @Provides
    fun provideLoginUseCase(userRepository: IUserRepository): LoginUseCase =
        LoginUseCase(userRepository)

    @Provides
    fun provideGetLoggedInUserUseCase(userRepository: IUserRepository): GetLoggedInUserUseCase =
        GetLoggedInUserUseCase(userRepository)
}