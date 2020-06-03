package com.example.theweather.data.network

import com.example.theweather.data.network.Responses.TempHourlyForecastResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataAPI {

    @GET("forecast?")
    suspend fun getHourlyForecast(
        @Query("q") cityName : String,
        @Query("units") unit : String,
        @Query("appid") appId : String

    ) : Response<TempHourlyForecastResponse>


    companion object{
        operator fun invoke() : WeatherDataAPI{
            return Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/forecast?")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherDataAPI::class.java)
        }
    }
}