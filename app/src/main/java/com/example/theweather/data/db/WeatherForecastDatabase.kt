package com.example.theweather.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theweather.data.db.entities.DailyForecast
import com.example.theweather.data.db.entities.HourlyForecast

@Database(
    entities = [HourlyForecast::class, DailyForecast::class],
    version = 1
)
abstract class WeatherForecastDatabase : RoomDatabase() {

    abstract fun hourlyForecastDao(): HourlyForecastDao
    abstract fun dailyForecastDao(): DailyForecastDao

    companion object {

        @Volatile
        private var instance: WeatherForecastDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeatherForecastDatabase::class.java,
                "WeatherForecastDatabase.db"
            ).build()
    }
}