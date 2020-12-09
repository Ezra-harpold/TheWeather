package com.harpold.theweather.data.db

import androidx.room.*
import com.harpold.theweather.data.db.TypeConverters.WeatherTypeConverter
import com.harpold.theweather.data.entities.CurrentForecast
import com.harpold.theweather.data.entities.HourlyForecast

@Database(
    entities = [HourlyForecast::class, CurrentForecast::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(WeatherTypeConverter::class)
abstract class WeatherForecastDatabase : RoomDatabase() {

    abstract fun hourlyForecastDao(): HourlyForecastDao
    abstract fun currentForecastDao(): CurrentForecastDao


}