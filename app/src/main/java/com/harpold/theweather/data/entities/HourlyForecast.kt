package com.harpold.theweather.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harpold.theweather.data.db.TypeConverters.WeatherTypeConverter
import com.harpold.theweather.data.entities.tempEntites.main
import com.harpold.theweather.data.entities.tempEntites.rain
import com.harpold.theweather.data.entities.tempEntites.weather
import com.harpold.theweather.data.entities.tempEntites.wind

/**
 *
 */
@Entity
data class HourlyForecast (
    @PrimaryKey
    var dt_txt: String,
    var dt: Long,
    var latitude: String,
    var longitude: String,
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