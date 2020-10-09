package com.example.theweather.ui

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.theweather.R
import com.example.theweather.util.Coroutines.Coroutines
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.tabs.TabLayoutMediator


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val numberOfFragments = 3

    private val mainViewModle: MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.main_activity_tool_bar))
        supportActionBar?.setDisplayShowTitleEnabled(false)


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
        }
            .attach()


       //Coroutines.main {
        // val forecast = mainViewModle.forecast.await()
         // forecast.observe(this, Observer {
         // Toast.makeText(this,it[0].weather[0].main
          //    .toString(),Toast.LENGTH_LONG).show()
           // })
       // }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)

        return true
    }

}


