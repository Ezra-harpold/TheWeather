package com.harpold.theweather.di

import android.content.Context
import com.harpold.theweather.data.network.NetworkConnectionTest
import com.harpold.theweather.data.network.WeatherDataAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApiModule {






   @Provides
    fun provideNetworkConnectionTest(@ApplicationContext appContext: Context) : NetworkConnectionTest{
     return NetworkConnectionTest(appContext)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext appContext: Context) : OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(provideNetworkConnectionTest(appContext))
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
