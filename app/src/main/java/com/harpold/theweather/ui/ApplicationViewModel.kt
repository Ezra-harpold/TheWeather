package com.harpold.theweather.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class ApplicationViewModel (application: Application) : AndroidViewModel(application){
    private val locationLiveDate = LocationLiveData(application)
     fun getLocation() = locationLiveDate
}