package com.harpold.theweather.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.harpold.theweather.ui.LocationLiveData

class ApplicationViewModel (application: Application) : AndroidViewModel(application){
    private val locationLiveDate =
        LocationLiveData(application)
     fun getLocation() = locationLiveDate
}