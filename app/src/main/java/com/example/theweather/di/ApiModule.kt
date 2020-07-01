package com.example.theweather.di

import android.content.Context
import com.example.theweather.data.network.NetworkConnectionTest
import com.example.theweather.data.network.WeatherDataAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {



   // @Provides
    //fun provideNetworkConnectionTest(context: Context) : NetworkConnectionTest{
    //    return NetworkConnectionTest(context)
   // }

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            //.addInterceptor(NetworkConnectionTest())
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDataAPI(retrofit: Retrofit): WeatherDataAPI{
        return retrofit.create(WeatherDataAPI::class.java)
    }
}
