package com.harpold.theweather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harpold.theweather.data.entities.HourlyForecast
import com.harpold.theweather.data.repositories.HourlyRepository
import com.harpold.theweather.util.Coroutines.Coroutines
import com.harpold.theweather.util.lazyDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivityViewModel @ViewModelInject constructor (
    private val hourlyRepository: HourlyRepository
): ViewModel(){
    lateinit var tomorrowForecast: MutableLiveData<List<HourlyForecast>>


    fun data(Data: Int, Lat: String, Long: String) {

}



        fun getForecastData(lat: String, long: String){
            viewModelScope.launch {
                hourlyRepository.queryHourlyForecastByLocation(lat,long)
            }
        }}




