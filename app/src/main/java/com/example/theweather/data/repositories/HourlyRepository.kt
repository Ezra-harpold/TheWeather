package com.example.theweather.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.theweather.data.db.HourlyForecastDao
import com.example.theweather.data.db.entities.HourlyForecast
import com.example.theweather.data.network.SafeApiCall
import com.example.theweather.data.network.WeatherDataAPI
import com.example.theweather.util.Coroutines.Coroutines
import com.example.theweather.util.formatDateTime

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HourlyRepository @Inject constructor(
    private val api: WeatherDataAPI,
    private val dao: HourlyForecastDao

) : SafeApiCall() {

    private val hourlyForecastList= MutableLiveData<List<HourlyForecast>>()
    private val location: String = "Baraboo"
    //private val unit: String = "imperial"
    private val apiKey: String = "221e684022fb29ca1d952493baeba98f"

            init{
                hourlyForecastList.observeForever{
                    saveForecast(it)

                }
            }

    suspend fun getHourlyForecast(date: Int) : LiveData<List<HourlyForecast>>{

        return withContext(Dispatchers.IO){
            queryHourlyForecast()

            dao.getAll()
        }
    }

    private suspend fun queryHourlyForecast() {

            try {
                val queryResponse = WeatherApiCall {
                    api.getHourlyForecast(location,
                        //unit,
                        apiKey)}
                val Result = queryResponse.list
                println(Result.size)

                for (item in Result){
                   val dateTime = formatDateTime(item.dt_txt)
                  item.date = dateTime.dayOfMonth
                    println(item.date)
                }

                hourlyForecastList.postValue(Result)
            }catch (e: Exception) {
                e.printStackTrace()
            }

    }

    private fun saveForecast(hourlyForecast: List<HourlyForecast>) {
        Coroutines.io {
            dao.saveAllHourlyForecasts(hourlyForecast)
        }
    }


}