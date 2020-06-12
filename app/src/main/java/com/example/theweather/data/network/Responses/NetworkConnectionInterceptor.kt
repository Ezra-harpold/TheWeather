package com.example.theweather.data.network.Responses

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor  {

    private val appContext = context.applicationContext


    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

    private fun checkConnection() : Boolean{
        TODO("Not yet implemented")
    }
}