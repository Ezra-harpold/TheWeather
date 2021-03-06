package com.harpold.theweather.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harpold.theweather.data.db.TypeConverters.WeatherTypeConverter
import com.harpold.theweather.data.entities.embeddedEntites.main
import com.harpold.theweather.data.entities.embeddedEntites.rain
import com.harpold.theweather.data.entities.embeddedEntites.weather
import com.harpold.theweather.data.entities.embeddedEntites.wind

/**
 *
 */
@Entity
data class HourlyForecast (
    @PrimaryKey(autoGenerate = false)
    var dt_txt: String,

    var dt: Long,
    var latitude: String,
    var longitude: String,
    var date: Int,

    @Embedded
    var main: main? = null,
    @Embedded
    var wind: wind? = null,
    @Embedded
    var rain: rain? = null,
    @TypeConverters(WeatherTypeConverter::class)
    var weather: List<weather>
)