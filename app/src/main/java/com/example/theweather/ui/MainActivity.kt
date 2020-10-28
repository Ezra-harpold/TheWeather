package com.example.theweather.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.theweather.R
import com.example.theweather.util.Coroutines.Coroutines
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.content.pm.PackageManager


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val LOCATION_PERMISSION_CODE = 500

    private val numberOfFragments = 3

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


        requestLocationUpdatesPermission()

        
      // Coroutines.main {
       //  val forecast = mainViewModel.forecast.await()
        //  forecast.observe(this, Observer {
         // Toast.makeText(this,it.size
          //    .toString(), Toast.LENGTH_LONG).show()
           // })
       // }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)

        return true
    }

    private fun requestLocationUpdatesPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates()
        } else{
            val locationPermissionRequest = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
            requestPermissions(locationPermissionRequest, LOCATION_PERMISSION_CODE)
        }
    }

    private fun requestLocationUpdates(){
        appViewModel.getLocation().observe(this, Observer {
            Toast.makeText(this,it.long , Toast.LENGTH_LONG).show()
        })
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


