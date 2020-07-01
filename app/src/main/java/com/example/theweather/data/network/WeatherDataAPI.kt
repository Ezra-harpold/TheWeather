package com.example.theweather.data.network

import com.example.theweather.data.network.Responses.HourlyForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataAPI {

    @GET("forecast?")
    suspend fun getHourlyForecast(
        @Query("q") cityName : String,
      //  @Query("units") unit : String,
        @Query("appid") apiKey : String

    ) : Response<HourlyForecastResponse>


    ///companion object{
     //   operator fun invoke(
      //      networkConnectionTest: NetworkConnectionTest
      //  ) : WeatherDataAPI{

        //    val okHttpclient = OkHttpClient.Builder()
        //    .addInterceptor(networkConnectionTest)
          //  .build()

           // return Retrofit.Builder()
           //     .client(okHttpclient)
            //    .baseUrl("http://api.openweathermap.org/data/2.5/")
             //   .addConverterFactory(GsonConverterFactory.create())
             //   .build()
              //  .create(WeatherDataAPI::class.java)
      //  }
   // }
}