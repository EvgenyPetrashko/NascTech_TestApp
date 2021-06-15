package com.evgeny_petrashko.nasctech_testapp.network

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherObject(
    @ColumnInfo(name = "location")
    var location: String?){
    @PrimaryKey
    @ColumnInfo(name = "zip")
    var zip: Int = 0
    @ColumnInfo(name = "temperature")
    var temperature: Double = 0.0
    @ColumnInfo(name = "wind_speed")
    var wind_speed: Double = 0.0
    @ColumnInfo(name = "humidity")
    var humidity : Int = 0
    @ColumnInfo(name = "visibility")
    var visibility: String = ""
    @ColumnInfo(name = "time_sunrise")
    var time_sunrise: String = ""
    @ColumnInfo(name = "time_sunset")
    var time_sunset: String = ""
    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0L
}
