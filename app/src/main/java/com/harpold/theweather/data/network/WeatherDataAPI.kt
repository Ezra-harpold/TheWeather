package com.harpold.theweather.data.network

import com.harpold.theweather.data.entities.CurrentForecast
import com.harpold.theweather.data.network.Responses.CurrentForecastResponse
import com.harpold.theweather.data.network.Responses.HourlyForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataAPI {

    @GET("forecast?")
    suspend fun getHourlyForecastByCityName(
        @Query("q") cityName : String,
      //  @Query("units") unit : String,
        @Query("appid") apiKey : String

    ) : Response<HourlyForecastResponse>

    @GET("forecast?")
    suspend fun getHourlyForecastByLatLon(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        //  @Query("units") unit : String,
        @Query("appid") apiKey: String
    ) : Response<HourlyForecastResponse>

    @GET("weather?")
    suspend fun getCurrentForecastByLatLon(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        //  @Query("units") unit : String,
        @Query("appid") apiKey: String
    ) :Response<CurrentForecastResponse>


}