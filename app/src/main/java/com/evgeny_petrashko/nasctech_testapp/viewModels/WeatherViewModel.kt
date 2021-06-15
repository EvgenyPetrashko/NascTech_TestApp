package com.evgeny_petrashko.nasctech_testapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject
import com.evgeny_petrashko.nasctech_testapp.repositories.WeatherRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class WeatherViewModel(private val repo: WeatherRepository): ViewModel() {

    private val weatherInfo: MutableLiveData<WeatherObject> by lazy {
        MutableLiveData<WeatherObject>()
    }

    fun refresh(zip: String){
        if (checkZipValidity(zip)){
            viewModelScope.launch {
                repo.getWeatherFromServer(zip = zip, mutableLiveData = weatherInfo)
            }
        }
    }

    fun getWeather(): MutableLiveData<WeatherObject> {
        return weatherInfo
    }

    fun checkZipValidity(zip: String): Boolean{
        return try {
            zip.toInt()
            true
        }catch (e: Exception){
            false
        }
    }

}

val viewModelModule = module {
    //viewModel { WeatherViewModel(get()) }
    single { WeatherViewModel(get()) }
}