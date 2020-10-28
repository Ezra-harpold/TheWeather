package com.example.theweather.data.db.TypeConverters

import androidx.room.TypeConverter
import com.example.theweather.data.entities.tempEntites.weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherTypeConverter {

    @TypeConverter
    fun weatherToString(list: List<weather>) : String{
        val gson = Gson()
        val type= object : TypeToken<List<weather>>() {}.type
        return gson.toJson(list, type)
    }
    @TypeConverter
    fun weatherFromString(string: String):List<weather>{
        val gson = Gson()
        val type = object : TypeToken<List<weather>>() {}.type
        return gson.fromJson(string,type)
    }

}