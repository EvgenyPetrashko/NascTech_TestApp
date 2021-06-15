package com.evgeny_petrashko.nasctech_testapp.repositories

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.MutableLiveData
import com.evgeny_petrashko.nasctech_testapp.databse.WeatherDao
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
    single{WeatherRepository(get(), get())}
    //factory { WeatherRepository(get(), get()) }
}

class WeatherRepository(val weatherService: WeatherService, val weatherDao: WeatherDao) {
    private val API: String = "2a3ef1ce7aa8026db782522f1b604aee"
    private val pattern: String = "MM/dd/yyyy hh:mm:ss a"

    suspend fun getWeatherFromServer(zip: String, mutableLiveData: MutableLiveData<WeatherObject>) {
        withContext(Dispatchers.IO){
            weatherService.getCurrentWeather(zip = zip, appid = API).enqueue(object: Callback<WeatherResponse>{
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        val weatherObject = weatherResponse!!.translateToWeatherObject(zip, pattern)
                        mutableLiveData.value = weatherObject

                        if (weatherDao.findZip(zip= zip.toInt()) == null){
                            weatherDao.insert(weatherObject)
                        }else{
                            weatherDao.update(weatherObject)
                        }

                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    var weatherObject = weatherDao.findZip(zip = zip.toInt())
                    if (weatherObject != null)
                        mutableLiveData.value = weatherObject
                }
            })
        }

    }
}