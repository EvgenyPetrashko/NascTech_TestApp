package com.evgeny_petrashko.nasctech_testapp.repositories

import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject
import com.evgeny_petrashko.nasctech_testapp.network.WeatherService
import com.evgeny_petrashko.nasctech_testapp.network.jsonObjects.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


val weatherModule = module {
    factory { WeatherRepository(get()) }
}

class WeatherRepository(val weatherService: WeatherService) {
    private val API: String = "2a3ef1ce7aa8026db782522f1b604aee"
    private val pattern: String = "MM/dd/yyyy hh:mm:ss a"

    suspend fun getWeather(zip: String, mutableLiveData: MutableLiveData<WeatherObject>) {
        withContext(Dispatchers.IO){
            weatherService.getCurrentWeather(zip = zip, appid = API).enqueue(object: Callback<WeatherResponse>{
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        val weatherObject = WeatherObject(weatherResponse?.name)
                        weatherObject.temperature = FromKelvinToFahrenheit(weatherResponse?.main?.temp!!)
                        weatherObject.wind_speed = weatherResponse.wind?.speed!!
                        weatherObject.humidity = weatherResponse.main!!.humidity
                        weatherObject.visibility = capitalizeString(weatherResponse.weather[0].description!!)
                        var ms = (weatherResponse.sys!!.sunrise * 1000).toLong()
                        weatherObject.time_sunrise = millisecondsToDate(ms) + " UTC"
                        ms = (weatherResponse.sys!!.sunset * 1000).toLong()
                        weatherObject.time_sunset = millisecondsToDate(ms) + " UTC"
                        mutableLiveData.value = weatherObject
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

    }

    fun millisecondsToDate(ms: Long): String{
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
        return simpleDateFormat.format(ms)
    }

    fun capitalizeString(report: String): String{
        return report[0].uppercaseChar() + report.substring(1)
    }

    fun FromKelvinToFahrenheit(kelvin: Double): Double{
        return (kelvin * 9)/5 - 459.67
    }

}