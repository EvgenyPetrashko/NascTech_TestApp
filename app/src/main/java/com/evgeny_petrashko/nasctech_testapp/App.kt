package com.evgeny_petrashko.nasctech_testapp

import android.app.Application
import com.evgeny_petrashko.nasctech_testapp.network.networkModule
import com.evgeny_petrashko.nasctech_testapp.repositories.weatherModule
import com.evgeny_petrashko.nasctech_testapp.viewModels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        println("\n\n\n APP \n\n\n")
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, networkModule, weatherModule))
        }
    }
}