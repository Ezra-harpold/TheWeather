package com.example.theweather.data.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionTest (context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

    private fun checkConnection() :{
        TODO("Not yet implemented")
    }
}