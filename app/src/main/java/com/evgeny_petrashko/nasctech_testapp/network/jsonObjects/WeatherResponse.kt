package com.evgeny_petrashko.nasctech_testapp.network.jsonObjects

import android.icu.text.SimpleDateFormat
import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList


class Clouds {

    @SerializedName("all")
    @Expose
    var all: Int? = 0

}

class Coord {

    @SerializedName("lon")
    @Expose
    var lon: Double = 0.0
    @SerializedName("lat")
    @Expose
    var lat: Double = 0.0

}

class Main {
    @SerializedName("temp")
    @Expose
    var temp: Double = 0.0
    @SerializedName("feels_like")
    @Expose
    var feelsLike: Double = 0.0
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double = 0.0
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double = 0.0
    @SerializedName("pressure")
    @Expose
    var pressure: Int = 0
    @SerializedName("humidity")
    @Expose
    var humidity: Int = 0
}

class Sys {
    @SerializedName("type")
    @Expose
    var type: Int = 0
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("sunrise")
    @Expose
    var sunrise: Int = 0
    @SerializedName("sunset")
    @Expose
    var sunset: Int = 0
}

class Weather {
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("main")
    @Expose
    var main: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("icon")
    @Expose
    var icon: String? = null
}

class WeatherResponse {

    @SerializedName("coord")
    @Expose
    var coord: Coord? = null
    @SerializedName("weather")
    @Expose
    var weather: List<Weather> = ArrayList<Weather>()
    @SerializedName("base")
    @Expose
    var base: String? = null
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("visibility")
    @Expose
    var visibility: Int = 0
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    @SerializedName("dt")
    @Expose
    var dt: Int = 0
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
    @SerializedName("timezone")
    @Expose
    var timezone: Int = 0
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("cod")
    @Expose
    var cod: Int = 0

    fun translateToWeatherObject(zip: String, pattern: String): WeatherObject{
        val weatherObject = WeatherObject(this.name)
        weatherObject.temperature = roundOffDecimal(FromKelvinToFahrenheit(this.main!!.temp))
        weatherObject.wind_speed = roundOffDecimal(this.wind!!.speed)
        weatherObject.humidity = this.main!!.humidity
        weatherObject.visibility = capitalizeString(this.weather[0].description!!)
        var ms = (this.sys!!.sunrise.toLong() * 1000)
        weatherObject.time_sunrise = millisecondsToDate(pattern, ms) + " UTC"
        ms = (this.sys!!.sunset.toLong() * 1000)
        weatherObject.time_sunset = millisecondsToDate(pattern, ms) + " UTC"
        weatherObject.zip = zip.toInt()
        weatherObject.timestamp = Calendar.getInstance().timeInMillis
        return weatherObject
    }

    private fun millisecondsToDate(pattern:String, ms: Long): String{
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        return simpleDateFormat.format(ms)
    }

    private fun capitalizeString(report: String): String{
        return report[0].uppercaseChar() + report.substring(1)
    }

    private fun FromKelvinToFahrenheit(kelvin: Double): Double{
        return (kelvin * 9)/5 - 459.67
    }

    private fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).replace(',','.').toDouble()
    }
}

class Wind {

    @SerializedName("speed")
    @Expose
    var speed: Double = 0.0
    @SerializedName("deg")
    @Expose
    var deg: Int = 0
    @SerializedName("gust")
    @Expose
    var gust: Double = 0.0

}