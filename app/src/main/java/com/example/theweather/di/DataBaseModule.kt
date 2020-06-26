package com.example.theweather.di

import android.app.Application
import androidx.room.Room
import com.example.theweather.data.db.HourlyForecastDao
import com.example.theweather.data.db.WeatherForecastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideWeatherForecastDatabase(application: Application) : WeatherForecastDatabase{
        return Room.databaseBuilder(
            application,
            WeatherForecastDatabase::class.java,
            "WeatherForecastDatabase.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideHourlyForecastDao(weatherForecastDatabase: WeatherForecastDatabase) :HourlyForecastDao{
        return weatherForecastDatabase.hourlyForecastDao()
    }
}