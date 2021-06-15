package com.evgeny_petrashko.nasctech_testapp

import android.app.Application
import android.content.Context
import com.evgeny_petrashko.nasctech_testapp.databse.databaseModule
import com.evgeny_petrashko.nasctech_testapp.network.networkModule
import com.evgeny_petrashko.nasctech_testapp.repositories.weatherModule
import com.evgeny_petrashko.nasctech_testapp.viewModels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule, networkModule, weatherModule, databaseModule))
        }
    }
}