package com.example.theweather.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionTest (context: Context) : Interceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        TODO("Not yet implemented")
    }

    /**
     * We use this function to check if the device is connected to the internet
     */
     private fun checkConnection() :Boolean{

        // We use this Boolean is used to track weather or not the device is connected to the internet
        var isConnected = false

        // Here we get an instance of ConnectivityManager we need this to get the network and networkCapabilities
        val connectionManager =
             appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        // Here we use connectionManager to get an instance of Network
        val network =
                connectionManager.activeNetwork ?: return false

        // This is where we use the connectionManager and network to get an instance of NetworkCapabilities
        val networkCapabilities  =
                connectionManager.getNetworkCapabilities(network) ?: return false

            /**
             * Here we use the instance of NetworkCapabilities to see if the device the app is
             * installed on is connected to a network. If it is we change the var isConnected to true
            */
            isConnected = when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
         return isConnected
        }


}