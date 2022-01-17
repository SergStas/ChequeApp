package com.example.chequeapp

import android.app.Application
import android.widget.Toast
import com.example.chequeapp.di.modules.app.AppModule
import com.example.chequeapp.di.DaggerIAppComponent
import com.example.chequeapp.di.IAppComponent

class App: Application() {
    companion object {
        lateinit var instance: App

        fun toast(msg: String) =
            Toast.makeText(instance, msg, Toast.LENGTH_LONG).show()
    }

    lateinit var appComponent: IAppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerIAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}