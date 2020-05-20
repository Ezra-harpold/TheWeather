package com.example.theweather.data.db.entities

import androidx.room.Entity

@Entity
 data class HourlyForecast (
 var id: String? = null,
 var hour: Int? = null,
 var date: Int? = null,
 var temp: Double? = null,
 var feels_like: Double? = null,
 var temp_min: Double? = null,
 var temp_max: Double? = null,
 var icon: String? = null,
 var humidity: Int? = null,
 var weather_id: Int? = null,
 var weather: String? = null,
 var weather_description: String? = null,
 var weather_icon: String? = null,
 var wind_speed: Double? = null,
 var pressure: Int? = null,
 var wind_direction: Int? = null,
 var has_rained: Boolean = false,
 var amount_of_rain: Double? = null
)


