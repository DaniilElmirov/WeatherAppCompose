package com.elmirov.weatherappcompose.domain.entity

import java.util.Calendar

data class Weather(
    val tempCelsius: Float,
    val conditionText: String,
    val conditionUrl: String,
    val date: Calendar,
)
