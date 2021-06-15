package com.evgeny_petrashko.nasctech_testapp.databse

import androidx.room.*
import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject

@Dao
interface WeatherDao {

    @Delete
    fun delete(weatherObject: WeatherObject)

    @Update
    fun update(weatherObject: WeatherObject)

    @Insert
    fun insert(weatherObject: WeatherObject)

    @Query("SELECT * FROM WeatherObject WHERE zip = :zip")
    fun findZip(zip: Int): WeatherObject
}