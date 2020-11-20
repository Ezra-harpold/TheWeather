package com.harpold.theweather.ui

import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.harpold.theweather.R
import com.harpold.theweather.ui.viewModels.ApplicationViewModel
import com.harpold.theweather.ui.viewModels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_CODE = 500


    private val numberOfFragments = 3

    // This is the name of the sharedPreferences file we will store
    // the location coordinates we observe
    private val sharedPreferencesFile: String = "com.harpold.theweather.locationSharedPreferences"

    // This is the sharedPreferences we use to save location coordinates
    private lateinit var mPreferences: SharedPreferences

    // These are the keys sharedPreferences uses to access and store coordinates
    private val LAT_KEY = "lat key"
    private val LONG_KEY = "long key"

    private val mainViewModel: MainActivityViewModel by viewModels()
    private val appViewModel: ApplicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_activity_tool_bar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //TODO remove OssLicenses from gradle
        //startActivity(Intent(this, OssLicensesMenuActivity::class.java))

        val fragmentForecastAdapter = FragmentForecastAdapter(this, numberOfFragments)
        fragmentForecastViewpager2.adapter = fragmentForecastAdapter

        val tabLayout:TabLayout = findViewById(R.id.main_activity_tab_layout)

        TabLayoutMediator(tabLayout, fragmentForecastViewpager2){tab, position ->
            when(position){
                0 -> tab.text = "Today"
                1 -> tab.text ="Tomorrow"
                2 -> tab.text = "Five Day"
            }
        }.attach()

        // Here we initialize mPreferences
        mPreferences = getSharedPreferences(sharedPreferencesFile, Context.MODE_PRIVATE)


        // This is where we ask the user for location permissions
        requestLocationUpdatesPermission()



        
     // Coroutines.main {
      //  val forecast = mainViewModel.forecast.await()
       // forecast.observe(this, Observer {
        //  Toast.makeText(this,it.size
         //     .toString(), Toast.LENGTH_LONG).show()
          //  })
        //}


    }

    // TODO Finish onCreateOptionsMenu to support search and options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)

        return true
    }

    // Here we request locations permission
    private fun requestLocationUpdatesPermission(){
        if (ContextCompat.checkSelfPermission(this!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // If we are granted location permission by user we request location updates
            requestLocationUpdates()
        } else{
            // If we don't have location permission request them
            val locationPermissionRequest = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(locationPermissionRequest, LOCATION_PERMISSION_CODE)
        }
    }

    

    // With this function we observe locationLiveData and save is as needed
    private fun requestLocationUpdates(){
        // Here we observe the locationLiveData we expose though the appViewModel
        appViewModel.getLocation().observe(this, Observer {
            // This is for debugging
            println(it.lat)
            println(it.long)
            // Check is sharedPreferences contains location coordinates
            if (mPreferences.contains(LAT_KEY)&& mPreferences.contains(LONG_KEY)){
                val savedLat = mPreferences.getString(LAT_KEY, null)
                val savedLong = mPreferences.getString(LONG_KEY, null)
                // Check if location coordinates needs to be updated
                if (needToUpDateLocation(it.lat,it.long,savedLat,savedLong)){
                    // If the coordinates we get from the locationLiveData are far enough for
                    // needToUpdateLocation to return true save them
                    saveLocation(it.lat,it.long)

                }else{

                    // This is just for debugging
                    println("location does not need to be updated")
                    Toast.makeText(this , "location does not need to be updated",Toast.LENGTH_LONG).show()
                }
            }else
                // If there is no coordinates saved in sharedPreferences save the coordinates we get
                //from locationLiveData
                saveLocation(it.lat, it.long)
        })
    }

    // This function is used to save the new location in sharedPreferences
    private fun saveLocation(lat: String, long: String){
        val editor = mPreferences.edit()
        editor.putString(LAT_KEY, lat)
        editor.putString(LONG_KEY, long)
        editor.apply()
        Toast.makeText(this, lat,Toast.LENGTH_LONG).show()
        //Here we use the new lat and long  to get new forecast data based on the new location that
        //we are being saving
        mainViewModel.getForecastDataByLocation(lat,long)
        println("new location = ${lat} ${long}")
    }


    // This is function is used to check if the current location if far enough from the saved location to be updated
    private fun needToUpDateLocation(lat1: String, long1: String, lat2: String?, long2: String?) : Boolean {
        // This is the number of meters in five miles
        // TODO change fiveMilesInMeters to a greater distance
        val fiveMilesInMeters = 8046.72

        val loc1 = Location("")
        loc1.latitude = lat1.toDouble()
        loc1.longitude = long1.toDouble()

        val loc2 = Location("")
        if (lat2 != null) {
            loc2.latitude = lat2.toDouble()
        }else{ return true}

        if (long2 != null) {
            loc2.longitude = long2.toDouble()
        }else{ return true}

        val distanceInMeters =loc1.distanceTo(loc2)
        return distanceInMeters > fiveMilesInMeters
    }




    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            LOCATION_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //
                    requestLocationUpdates()
                }
                //TODO do something if location permissions is not granted
            }
        }
    }
}


