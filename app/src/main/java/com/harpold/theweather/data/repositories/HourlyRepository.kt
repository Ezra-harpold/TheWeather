package com.harpold.theweather.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harpold.theweather.data.db.CurrentForecastDao
import com.harpold.theweather.data.db.HourlyForecastDao
import com.harpold.theweather.data.entities.CurrentForecast
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
    private val hourlyForecastDao: HourlyForecastDao,
    private val currentForecastDao: CurrentForecastDao

) : SafeApiCall() {

    private val hourlyForecastList= MutableLiveData<List<HourlyForecast>>()
    private val location: String = "Baraboo"
    //private val unit: String = "imperial"
    private val apiKey: String = "221e684022fb29ca1d952493baeba98f"



    suspend fun getHourlyForecast(date: Int) : LiveData<List<HourlyForecast>>{

        return withContext(Dispatchers.IO){
            //queryHourlyForecast()

            hourlyForecastDao.getHourlyForecastByDate(date)
        }
    }
    suspend fun queryForecastByLocation(lat: String, long: String){

        return withContext(Dispatchers.IO){
            queryHourlyForecast(lat, long)
            queryCurrentForecast(lat, long)
        }
    }

    private suspend fun queryHourlyForecast(lat: String, long: String) {
            try {
                val hourlyForecastResponse = WeatherApiCall {
                    api.getHourlyForecastByLatLon(lat,long, //unit,
                         apiKey)}
                val Result = hourlyForecastResponse.list
                //println(Result.size)

                for (item in Result){
                   val dateTime = formatDateTime(item.dt)
                    item.date = dateTime.dayOfMonth
                    item.latitude = lat
                    item.longitude = long
                    //println(item.date)
                }

                deleteObsoleteData(lat, long)
                saveHourlyForecast(Result)
            }catch (e: Exception) {
                e.printStackTrace()
            }
    }

    private suspend fun queryCurrentForecast(lat: String, lon:String){
        try {
            val currentForecastResponse = WeatherApiCall {
                api.getCurrentForecastByLatLon(lat, lon, apiKey)}

            val currentForecastResult = currentForecastResponse.currentForecast

            saveCurrentForecast(currentForecastResult)
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun saveHourlyForecast(hourlyForecast: List<HourlyForecast>) {
        Coroutines.io {
            hourlyForecastDao.saveAllHourlyForecasts(hourlyForecast)
        }
    }

    private fun saveCurrentForecast(currentForecast: CurrentForecast){
        Coroutines.io {
            currentForecastDao.saveCurrentForecast(currentForecast)
        }
    }

    private fun deleteObsoleteData(lat: String, long: String){
        val epochInSeconds : Long = Instant.now().epochSecond
        hourlyForecastDao.deleteObsoleteDataByLocation(lat,long)
        hourlyForecastDao.deleteObsoleteDataByEpochTimestamp(epochInSeconds)

    }


}