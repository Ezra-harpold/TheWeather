package com.example.theweather.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

 data class DailyForecast (
    @PrimaryKey(autoGenerate = false)
    var id: Int
)