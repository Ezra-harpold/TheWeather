package com.harpold.theweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.harpold.theweather.data.entities.HourlyForecast

@Dao
interface HourlyForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllHourlyForecasts (hourlyForecast: List<HourlyForecast>)

    @Query("SELECT * FROM HourlyForecast")
    fun getAll() :LiveData<List<HourlyForecast>>

    @Query("SELECT * FROM HourlyForecast WHERE date LIKE :Date ")
    fun getHourlyForecastByDate(Date: Int) :LiveData<List<HourlyForecast>>

    @Query("SELECT * FROM HourlyForecast WHERE date LIKE :Date AND latitude LIKE :Lat AND longitude LIKE :Long")
    fun getHourlyForecastByDateAndLocation(Date: Int, Lat: String, Long: String) :LiveData<List<HourlyForecast>>

    @Query("DELETE  FROM HourlyForecast WHERE latitude != :Lat AND longitude != :Long ")
    fun deleteObsoleteDataByLocation(Lat: String, Long: String) //: Int

    @Query("DELETE FROM HourlyForecast WHERE dt < :EpochInSeconds")
    fun deleteObsoleteDataByEpochTimestamp(EpochInSeconds: Long) : Int

}