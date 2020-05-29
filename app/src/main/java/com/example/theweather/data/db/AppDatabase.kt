package com.example.theweather.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.theweather.data.db.entities.DailyForecast
import com.example.theweather.data.db.entities.HourlyForecast

@Database(
    entities = [HourlyForecast::class, DailyForecast::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

}