package com.example.theweather.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.theweather.data.db.TypeConverters.WeatherTypeConverter
import com.example.theweather.data.entities.tempEntites.main
import com.example.theweather.data.entities.tempEntites.rain
import com.example.theweather.data.entities.tempEntites.weather
import com.example.theweather.data.entities.tempEntites.wind

/**
 *
 */
@Entity
data class HourlyForecast (
    @PrimaryKey
    var dt_txt: String,

    var date: Int ,

    @Embedded
    var main: main? = null,
    @Embedded
    var wind: wind? = null,
    @Embedded
    var rain: rain? = null,
    @TypeConverters(WeatherTypeConverter::class)
    var weather: List<weather>
)