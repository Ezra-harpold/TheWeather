package com.example.theweather.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentForecastAdapter(hostActivity: AppCompatActivity, val fragmentCount: Int):
FragmentStateAdapter(hostActivity){


    override fun getItemCount(): Int {
        return fragmentCount
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> todaysForecastFragment.newInstance()
            1 -> tomorrowForecastFragment.newInstance()
            2 -> fiveDayForecastFragment.newInstance()
            else -> todaysForecastFragment.newInstance()
            }

        }

    }
