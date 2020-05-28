package com.example.theweather.data.db.entities.tempEntites

/**
 * This object exists to handle the GSON responses from the API.
 * The API contains multiple nest objects and I'm using Room to manage my database, and
 * I want to avoid querying embedded objects with Room for performance reasons so
 * this is just here to handle the data from the API and feed it to an object that I
 * will store in the database
 */
class TempHourlyForecast (
    var main: main? = null,
    var weather: List<weather>? = null,
    var wind: wind? = null,
    var rain: rain? = null,
    var dt_txt: String? = null
)