package com.harpold.theweather.ui.viewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harpold.theweather.data.entities.HourlyForecast
import com.harpold.theweather.data.repositories.HourlyRepository
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor (
    private val hourlyRepository: HourlyRepository
): ViewModel(){
    lateinit var tomorrowForecast: MutableLiveData<List<HourlyForecast>>


    fun data(Data: Int, Lat: String, Long: String) {

}



        fun getForecastDataByLocation(lat: String, long: String){
            viewModelScope.launch {
                hourlyRepository.queryForecastByLocation(lat,long)
            }
        }}




