package com.elmirov.weatherappcompose.domain.entity

data class Forecast(
    val currentWeather: Weather,
    val upcoming: List<Weather>,
)
