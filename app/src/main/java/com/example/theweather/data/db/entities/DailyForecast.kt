package com.example.theweather.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

 data class DailyForecast (
    @PrimaryKey(autoGenerate = false)
    var id: Int
)