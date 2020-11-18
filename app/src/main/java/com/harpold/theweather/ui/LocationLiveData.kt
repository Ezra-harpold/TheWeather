package com.harpold.theweather.ui

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.harpold.theweather.data.entities.LocationData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationLiveData(context: Context) : LiveData<LocationData>(){

    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onInactive() {
        super.onInactive()
        // End Location updates
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        // Start location updates
        fusedLocationProviderClient.lastLocation.addOnSuccessListener{
            location: Location? ->  location.also {
            if (it != null) {
                setLocation(it)
            }
        }
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback,null)
    }



    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(results: LocationResult?) {
            super.onLocationResult(results)
            results ?: return
            for (location in results.locations)
                setLocation(location)
        }
    }

    private fun setLocation(location: Location){
        value = LocationData(
            location.latitude.toString(),
            location.longitude.toString()
        )
    }

    companion object{
        private const val fiveMinutes : Long = 300000
        val locationRequest : LocationRequest = LocationRequest.create().apply {
            interval = fiveMinutes
            fastestInterval = fiveMinutes/10


            priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        }
    }
}