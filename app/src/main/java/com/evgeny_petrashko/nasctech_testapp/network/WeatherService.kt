package com.evgeny_petrashko.nasctech_testapp.network

import com.evgeny_petrashko.nasctech_testapp.network.jsonObjects.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getCurrentWeather(@Query("zip") zip: String, @Query("appid") appid: String): Call<WeatherResponse>
}