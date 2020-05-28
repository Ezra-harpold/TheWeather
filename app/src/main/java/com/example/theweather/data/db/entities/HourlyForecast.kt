package com.example.theweather.data.db.entities

import androidx.room.Entity
import com.example.theweather.data.db.entities.tempEntites.TempHourlyForecast


/**
 * This is the data class that represents the hourly forecast that will be stored in the database.
 * I'm using a separate object from the one the interacts with the API response because
 * that one contains multiple nested objects.
 * And this is the easiest way of collection the data from the API response without having
 * embedded relationships which the android documentation suggests to avoid,
 * if I took the data straight from the API response and put it in to database every time
 * I need data from the Hourly forecast have to ask the database for the hourly forecast
 * object and between one and four embedded objects. We will be using this data very frequently
 * in the app so this little extra work now should be worth avoiding  querying the database
 * from embedded objects so constantly.
 */
@Entity
data class HourlyForecast (
    var id: String? = null,
    var hour: Int? = null,
    var date: Int? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var pressure: Int? = null,
    var humidity: Int? = null,
    var weatherId: Int? = null,
    var weather: String? = null,
    var weatherDescription: String? = null,
    var weatherIcon: String? = null,
    var windSpeed: Double? = null,
    var windDirection: Int? = null,
    var willRain: Boolean = false,
    var amountOfRain: Double? = 0.0
)


