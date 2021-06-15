package com.evgeny_petrashko.nasctech_testapp.network

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.file.WatchService

val networkModule = module {
    val baseUrl = "https://api.openweathermap.org/data/2.5/"

    fun service(): Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideService(retrofit: Retrofit) : WeatherService{
        return retrofit.create(WeatherService::class.java)
    }

    single { service() }
    single { provideService(get()) }
}
