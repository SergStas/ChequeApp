package com.example.chequeapp.di

import com.example.chequeapp.di.modules.app.AppModule
import com.example.chequeapp.di.modules.DataModule
import com.example.chequeapp.di.modules.DomainModule
import com.example.chequeapp.di.modules.NetworkModule
import com.example.chequeapp.di.modules.app.newevent.AppNewEventModule
import com.example.chequeapp.ui.auth.AuthFragment
import com.example.chequeapp.ui.newevent.NewEventFragment
import com.example.chequeapp.ui.newevent.names.NewEventNamesFragment
import com.example.chequeapp.ui.newevent.users.NewEventParticipantsFragment
import com.example.chequeapp.ui.root.MainActivity
import dagger.Component

@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class,
    NetworkModule::class,
    AppNewEventModule::class,
])
interface IAppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(authFragment: AuthFragment)

    fun inject(newEventFragment: NewEventFragment)

    fun inject(newEventNamesFragment: NewEventNamesFragment)

    fun inject(newEventParticipantsFragment: NewEventParticipantsFragment)
}