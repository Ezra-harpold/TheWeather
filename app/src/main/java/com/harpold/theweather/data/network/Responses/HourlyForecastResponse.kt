package com.harpold.theweather.data.network.Responses

import com.harpold.theweather.data.entities.HourlyForecast

data class HourlyForecastResponse (
    val cod: Int,
    val list: List<HourlyForecast>
)