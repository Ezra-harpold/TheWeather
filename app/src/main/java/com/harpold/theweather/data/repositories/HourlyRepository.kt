package com.harpold.theweather.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harpold.theweather.data.db.HourlyForecastDao
import com.harpold.theweather.data.entities.HourlyForecast
import com.harpold.theweather.data.network.SafeApiCall
import com.harpold.theweather.data.network.WeatherDataAPI
import com.harpold.theweather.util.Coroutines.Coroutines
import com.harpold.theweather.util.formatDateTime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

class HourlyRepository @Inject constructor(
    private val api: WeatherDataAPI,
    private val dao: HourlyForecastDao

) : SafeApiCall() {

    private val hourlyForecastList= MutableLiveData<List<HourlyForecast>>()
    private val location: String = "Baraboo"
    //private val unit: String = "imperial"
    private val apiKey: String = "221e684022fb29ca1d952493baeba98f"



    suspend fun getHourlyForecast(date: Int) : LiveData<List<HourlyForecast>>{

        return withContext(Dispatchers.IO){
            //queryHourlyForecast()

            dao.getHourlyForecastByDate(date)
        }
    }
    suspend fun queryHourlyForecastByLocation(lat: String, long: String){

        return withContext(Dispatchers.IO){
            queryHourlyForecast(lat, long)
        }
    }

    private suspend fun queryHourlyForecast(lat: String, long: String) {
            try {
                val queryResponse = WeatherApiCall {
                    api.getHourlyForecastByLatLon(lat,long, //unit,
                         apiKey)}
                val Result = queryResponse.list
                println(Result.size)

                for (item in Result){
                   val dateTime = formatDateTime(item.dt)
                    item.date = dateTime.dayOfMonth
                    item.latitude = lat
                    item.longitude = long
                    println(item.date)
                }

                // TODO delete old saved forecast
                //  deleteObsoleteData(lat, long)
                saveForecast(Result)
            }catch (e: Exception) {
                e.printStackTrace()
            }
    }


    private fun saveForecast(hourlyForecast: List<HourlyForecast>) {
        Coroutines.io {
            dao.saveAllHourlyForecasts(hourlyForecast)
        }
    }

    private fun deleteObsoleteData(lat: String, long: String){
        val epochInSeconds : Long = Instant.now().epochSecond
        dao.deleteObsoleteDataByLocation(lat,long)
        dao.deleteObsoleteDataByEpochTimestamp(epochInSeconds)

    }


}