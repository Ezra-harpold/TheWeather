package com.harpold.theweather.ui.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.harpold.theweather.R

object ImageViewBindingAdapter {

    @BindingAdapter("imageCode")
    @JvmStatic
    fun bindImageCode(view: ImageView, code: String){
        // The loop to sets the imageViews Drawable resource based on the icon code we
        // get from the forecast entities, icon is is stored in the entity's embedded Weather object
        when (code) {
            "13d" -> view.setImageResource(R.drawable.icon_snow)
            "11d" -> view.setImageResource(R.drawable.icon_thunderstorm)
            "10d" -> view.setImageResource(R.drawable.icon_rainy)
            "09d" -> view.setImageResource(R.drawable.icon_rainy)
            "04d" -> view.setImageResource(R.drawable.icon_cloudy)
            "04n" -> view.setImageResource(R.drawable.icon_cloudy)
            "03d" -> view.setImageResource(R.drawable.icon_cloudy)
            "03n" -> view.setImageResource(R.drawable.icon_cloudy)
            "02d" -> view.setImageResource(R.drawable.ic_partly_sunny)
            "02n" -> view.setImageResource(R.drawable.icon_cloudy_night)
            "01d" -> view.setImageResource(R.drawable.icon_sunny)
            "01n" -> view.setImageResource(R.drawable.icon_moon)
            else -> view.setImageResource(R.drawable.icon_cloudy)

            
        }
    }}
