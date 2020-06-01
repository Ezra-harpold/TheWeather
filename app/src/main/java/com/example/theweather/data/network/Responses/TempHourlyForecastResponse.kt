package com.example.theweather.data.network.Responses

import com.example.theweather.data.db.entities.tempEntites.TempHourlyForecast

data class TempHourlyForecastResponse (
    val cod: Int,
    val list: List<TempHourlyForecast>
)