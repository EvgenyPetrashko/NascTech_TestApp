package com.evgeny_petrashko.nasctech_testapp.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evgeny_petrashko.nasctech_testapp.network.WeatherObject
import com.evgeny_petrashko.nasctech_testapp.network.jsonObjects.WeatherResponse
import com.evgeny_petrashko.nasctech_testapp.repositories.WeatherRepository
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Call

class WeatherViewModel(private val repo: WeatherRepository): ViewModel() {

    private val weatherInfo: MutableLiveData<WeatherObject> by lazy {
        MutableLiveData<WeatherObject>()
    }

    fun refresh(zip: String){
         viewModelScope.launch {
             repo.getWeather(zip = zip, mutableLiveData = weatherInfo)
         }
    }

    fun getWeather(): MutableLiveData<WeatherObject> {
        return weatherInfo
    }

}

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}