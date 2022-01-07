package com.example.chequeapp.di

import android.content.Context
import com.example.chequeapp.presentation.root.AbstractRootPageViewModel
import com.example.chequeapp.presentation.root.RootPageViewModel
import com.example.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    private var rootPageViewModel: AbstractRootPageViewModel? = null

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideRootPageViewModel(userRepository: IUserRepository): AbstractRootPageViewModel =
        rootPageViewModel ?: run {
            rootPageViewModel = RootPageViewModel(userRepository)
            rootPageViewModel!!
        }
}