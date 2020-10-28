package com.example.theweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.theweather.data.entities.HourlyForecast

@Dao
interface HourlyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllHourlyForecasts (hourlyForecast: List<HourlyForecast>)

    @Query("SELECT * FROM HourlyForecast")
    fun getAll() :LiveData<List<HourlyForecast>>

    @Query("SELECT * FROM HourlyForecast WHERE date LIKE :Date")
    fun getHourlyForecastByDate(Date: Int) : LiveData<List<HourlyForecast>>

    //@Query("SELECT * FROM HourlyForecast WHERE date LIKE :Date AND hour LIKE :Hour")
    //fun getHourlyForecastByDateAndHour(Hour: Int, Date: Int) : LiveData<HourlyForecast>


}