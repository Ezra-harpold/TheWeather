package com.harpold.theweather.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.harpold.theweather.data.db.TypeConverters.WeatherTypeConverter
import com.harpold.theweather.data.entities.embeddedEntites.main
import com.harpold.theweather.data.entities.embeddedEntites.weather
import com.harpold.theweather.data.entities.embeddedEntites.wind


const val IdForCurrentForecast = 0
@Entity
 data class CurrentForecast (

   // Having a constant value for the id along with the OnConflictStrategy of replace should mean
   // we only ever have one instance of currentForecast saved in are database
    @PrimaryKey(autoGenerate = false)
    var id: Int = IdForCurrentForecast,

    @Embedded
    var main: main? = null,
    @Embedded
    var wind: wind? = null,

    @TypeConverters(WeatherTypeConverter::class)
    var weather: List<weather>

    





)