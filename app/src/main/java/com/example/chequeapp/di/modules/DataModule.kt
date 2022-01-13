package com.example.chequeapp.di.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.data.repository.EventsRepository
import com.example.data.repository.UserRepository
import com.example.data.storage.IDeviceUserStorage
import com.example.data.storage.SharedPrefsUserStorage
import com.example.domain.repository.IEventsRepository
import com.example.domain.repository.IUserRepository
import com.example.network.IApiProvider
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    private var storage: IDeviceUserStorage? = null
    private var userRepository: IUserRepository? = null
    private var eventsRepository: IEventsRepository? = null

    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(
            "com.example.chequeapp",
            Context.MODE_PRIVATE,
        )

    @Provides
    fun provideUserStorage(sharedPreferences: SharedPreferences): IDeviceUserStorage =
        storage ?: run {
            storage = SharedPrefsUserStorage(sharedPreferences)
            storage!!
        }

    @Provides
    fun provideUserRepository(apiProvider: IApiProvider, userStorage: IDeviceUserStorage): IUserRepository =
        userRepository ?: run {
            userRepository = UserRepository(apiProvider, userStorage)
            userRepository!!
        }

    @Provides
    fun provideEventsRepository(apiProvider: IApiProvider, userRepository: IUserRepository): IEventsRepository =
        eventsRepository ?: run {
            eventsRepository = EventsRepository(apiProvider, userRepository)
            eventsRepository!!
        }
}