package com.example.chequeapp.di.modules

import com.example.network.IApiProvider
import com.example.network.ApiProvider
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    private var apiProvider: IApiProvider? = null

    @Provides
    fun provideApiProvider(): IApiProvider =
       apiProvider ?: run {
           apiProvider = ApiProvider()
           apiProvider!!
       }
}