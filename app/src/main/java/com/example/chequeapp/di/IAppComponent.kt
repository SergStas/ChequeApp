package com.example.chequeapp.di

import com.example.chequeapp.ui.auth.AuthFragment
import com.example.chequeapp.ui.root.MainActivity
import dagger.Component

@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class,
    NetworkModule::class,
])
interface IAppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(authFragment: AuthFragment)
}