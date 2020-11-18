package com.harpold.theweather.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

import java.time.format.DateTimeFormatter


fun formatDateTime(dt: Long): LocalDateTime{

    return LocalDateTime.ofInstant(Instant.ofEpochSecond(dt),  ZoneId.systemDefault())

}


