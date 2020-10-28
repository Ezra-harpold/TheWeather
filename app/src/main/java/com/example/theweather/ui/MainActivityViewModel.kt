package com.example.theweather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.theweather.data.repositories.HourlyRepository
import com.example.theweather.util.lazyDeferred
import javax.inject.Inject

class MainActivityViewModel @ViewModelInject constructor (
    private val hourlyRepository: HourlyRepository
): ViewModel(){


        val forecast by lazyDeferred {
            hourlyRepository.getHourlyForecast(15)
        }



}