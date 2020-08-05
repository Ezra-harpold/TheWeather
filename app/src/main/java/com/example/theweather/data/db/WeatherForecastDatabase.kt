package com.example.theweather.data.db

import android.content.Context
import androidx.room.*
import com.example.theweather.data.db.TypeConverters.WeatherTypeConverter
import com.example.theweather.data.db.entities.DailyForecast
import com.example.theweather.data.db.entities.HourlyForecast

@Database(
    entities = [HourlyForecast::class, DailyForecast::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(WeatherTypeConverter::class)
abstract class WeatherForecastDatabase : RoomDatabase() {

    abstract fun hourlyForecastDao(): HourlyForecastDao
    abstract fun dailyForecastDao(): DailyForecastDao


}