package com.harpold.theweather.data.entities.embeddedEntites

import com.google.gson.annotations.SerializedName

class rain (

    @SerializedName("3h")
    var amountOfRain: Double? = null
)