package com.example.theweather.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDateTime(dt_txt: String): LocalDateTime{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(dt_txt , formatter)
}