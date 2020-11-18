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
import com.harpold.theweather.util.Coroutines.Coroutines
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_CODE = 500

    private val numberOfFragments = 3

    private val sharedPreferencesFile: String = "com.harpold.theweather.locationSharedPreferences"

    private lateinit var mPreferences: SharedPreferences

    private val LAT_KEY = "lat key"

    private val LONG_KEY = "long key"

    private val mainViewModel: MainActivityViewModel by viewModels()

    private val appViewModel: ApplicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_activity_tool_bar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //startActivity(Intent(this, OssLicensesMenuActivity::class.java))

        val fragmentForecastAdapter = FragmentForecastAdapter(this,
            numberOfFragments)
        fragmentForecastViewpager2.adapter = fragmentForecastAdapter

        val tabLayout:TabLayout = findViewById(R.id.main_activity_tab_layout)

        TabLayoutMediator(tabLayout, fragmentForecastViewpager2){tab, position ->
            when(position){
                0 -> tab.text = "Today"
                1 -> tab.text ="Tomorrow"
                2 -> tab.text = "Five Day"
            }
        }.attach()

        mPreferences = getSharedPreferences(sharedPreferencesFile, Context.MODE_PRIVATE)

        requestLocationUpdatesPermission()



        
     // Coroutines.main {
      //  val forecast = mainViewModel.forecast.await()
       // forecast.observe(this, Observer {
        //  Toast.makeText(this,it.size
         //     .toString(), Toast.LENGTH_LONG).show()
          //  })
        //}


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)

        return true
    }

    private fun requestLocationUpdatesPermission(){
        if (ContextCompat.checkSelfPermission(this!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates()
        } else{
            val locationPermissionRequest = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(locationPermissionRequest, LOCATION_PERMISSION_CODE)
        }
    }

    


    private fun requestLocationUpdates(){
        appViewModel.getLocation().observe(this, Observer {
            println(it.lat)
            println(it.long)
            if (mPreferences.contains(LAT_KEY)&& mPreferences.contains(LONG_KEY)){

                val savedLat = mPreferences.getString(LAT_KEY, null)
                val savedLong = mPreferences.getString(LONG_KEY, null)
                if (needToUpDateLocation(it.lat,it.long,savedLat,savedLong)){
                    saveLocation(it.lat,it.long)

                    // TODO test this code out tomorrow first thing.
                  //  mainViewModel.getForecastData(it.lat,it.long)
                    // TODO if it works move on to observing the data in the fragments
                }else{

                    println("location does not need to be updated")
                    Toast.makeText(this , "location does not need to be updated",Toast.LENGTH_LONG).show()
                }
            }else
            saveLocation(it.lat, it.long)
        })
    }


    private fun saveLocation(lat: String, long: String){

        val editor = mPreferences.edit()
        editor.putString(LAT_KEY, lat)
        editor.putString(LONG_KEY, long)
        editor.apply()
        Toast.makeText(this, lat,Toast.LENGTH_LONG).show()
        mainViewModel.getForecastData(lat,long)
        println("new location = ${lat} ${long}")
    }


    private fun needToUpDateLocation(lat1: String, long1: String, lat2: String?, long2: String?) : Boolean {
        // This is the number of meters in five miles
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
                    requestLocationUpdates()
                }
            }
        }
    }
}


