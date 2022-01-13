package com.example.chequeapp

import android.app.Application
import com.example.chequeapp.di.modules.app.AppModule
import com.example.chequeapp.di.DaggerIAppComponent
import com.example.chequeapp.di.IAppComponent

class App: Application() {
    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerIAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}