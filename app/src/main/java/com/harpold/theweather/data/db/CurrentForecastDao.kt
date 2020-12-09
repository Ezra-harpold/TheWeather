package com.harpold.theweather.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.harpold.theweather.data.entities.CurrentForecast


@Dao
interface CurrentForecastDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCurrentForecast (currentForecast: CurrentForecast)

    @Query("SELECT * FROM CurrentForecast")
    fun getCurrentForecast() : LiveData<CurrentForecast>

}