package com.example.theweather.data.network

import com.example.theweather.data.network.Responses.TempHourlyForecastResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WeatherDataAPI {

    @GET("list")
    suspend fun getHourlyForecast() : Response<TempHourlyForecastResponse>


    companion object{
        operator fun invoke() : WeatherDataAPI{
            return Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/forecast?q=Baraboo&appid=221e684022fb29ca1d952493baeba98f")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherDataAPI::class.java)
        }
    }
}