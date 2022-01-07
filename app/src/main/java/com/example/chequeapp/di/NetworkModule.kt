package com.example.chequeapp.di

import com.example.domain.network.IApiProvider
import com.example.network.ApiProvider
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {
    @Provides
    fun provideApiProvider(): IApiProvider =
        ApiProvider()
}