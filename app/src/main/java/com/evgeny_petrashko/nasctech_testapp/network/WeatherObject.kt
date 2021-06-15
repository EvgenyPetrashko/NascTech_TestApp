package com.evgeny_petrashko.nasctech_testapp.network

data class WeatherObject(var location: String?) {
    var temperature: Double = 0.0
    var wind_speed: Double = 0.0
    var humidity : Int = 0
    var visibility: String = ""
    var time_sunrise: String = ""
    var time_sunset: String = ""
}
