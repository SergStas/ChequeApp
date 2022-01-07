package com.example.chequeapp.di

import dagger.Component

@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class,
    NetworkModule::class,
])
interface IAppComponent