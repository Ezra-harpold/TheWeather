package com.example.theweather.data.repositories

import com.example.theweather.data.db.entities.HourlyForecast
import com.example.theweather.data.db.entities.tempEntites.TempHourlyForecast
import com.example.theweather.util.formatDateTime

class hourlyRepository {


    /**
     *  This function sets the data for the HourlyForecast object that we get from the API.
     */
    private fun setData(hourlyForecast: HourlyForecast, tempForecast: TempHourlyForecast){

        /**
         * we wil use the dt_txt as the id for the hourly forecast
         */
        hourlyForecast.id = tempForecast.dt_txt

        hourlyForecast.temp = tempForecast.main?.temp
        hourlyForecast.feelsLike = tempForecast.main?.feels_like
        hourlyForecast.tempMax = tempForecast.main?.temp_max
        hourlyForecast.tempMin = tempForecast.main?.temp_min
        hourlyForecast.pressure = tempForecast.main?.pressure
        hourlyForecast.humidity = tempForecast.main?.humidity
        /**
         * The weather data is always in a list with only one element so when want to get that data
         * we just use get on the 0 element of the list
         */
        hourlyForecast.weatherId = tempForecast.weather?.get(index = 0)?.id
        hourlyForecast.weather = tempForecast.weather?.get(0)?.main
        hourlyForecast.weatherDescription = tempForecast.weather?.get(0)?.description
        hourlyForecast.weatherIcon = tempForecast.weather?.get(0)?.icon

        hourlyForecast.windSpeed = tempForecast.wind?.speed
        hourlyForecast.windDirection = tempForecast.wind?.deg
        /**
         * The willRain object is by default false in the data object, and rain object is only
         * present in a response in if there is a forecast for rain.
         * So if there is no instance of rain in the object than we leave the willRain object false
         * and set the amountOfRain to 0.0 so it is not null
        */
        if (tempForecast.rain!= null) {
            hourlyForecast.willRain = true
            hourlyForecast.amountOfRain = tempForecast.rain?.amountOfRain
        }else{
            hourlyForecast.amountOfRain = 0.0
        }
        /**
         * The id is or dt_txt is the time of data forecasted in ISO UTC
         * we take this string convert it to a localDateTime object so we can get the date and hour
         * when to data was forecasted.
         */
        val dateTime = hourlyForecast.id?.let { formatDateTime(it) }
        hourlyForecast.date = dateTime?.dayOfMonth
        hourlyForecast.hour = dateTime?.hour
    }
}