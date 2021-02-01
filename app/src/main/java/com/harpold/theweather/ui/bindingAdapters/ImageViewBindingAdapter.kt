package com.harpold.theweather.ui.bindingAdapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.harpold.theweather.R

object ImageViewBindingAdapter {

    @BindingAdapter("imageCode")
    @JvmStatic
    fun bindImageCode(view: ImageView, code: String){
        //TODO
        // Add when loop to set the imageViews Drawable resource based on the icon code we
        // get from the forecast entities, icon is is stored in the entity's embedded Weather object
        when {
            code =="11d" -> view.setImageResource(R.drawable.icon_thunderstorm)
        }
    }}
