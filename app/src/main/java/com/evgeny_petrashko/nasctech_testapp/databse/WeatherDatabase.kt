package com.evgeny_petrashko.nasctech_testapp.databse

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evgeny_petrashko.nasctech_testapp.App
import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Database(version = 1, entities = [WeatherObject::class])
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}

val databaseModule = module {
    fun provideDatabase(app: Context): WeatherDatabase {
        var db =  Room.databaseBuilder(app, WeatherDatabase::class.java, "weather.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        db.weatherDao()
        return db
    }

    fun provideCountriesDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}