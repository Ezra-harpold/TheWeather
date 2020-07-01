package com.example.theweather.data.network.Responses

import com.example.theweather.data.db.entities.HourlyForecast

data class HourlyForecastResponse (
    val cod: Int,
    val list: List<HourlyForecast>
)