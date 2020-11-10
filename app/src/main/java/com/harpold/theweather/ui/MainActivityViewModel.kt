package com.harpold.theweather.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.harpold.theweather.data.repositories.HourlyRepository
import com.harpold.theweather.util.lazyDeferred

class MainActivityViewModel @ViewModelInject constructor (
    private val hourlyRepository: HourlyRepository
): ViewModel(){


        val forecast by lazyDeferred {
            hourlyRepository.getHourlyForecast(15)
        }



}