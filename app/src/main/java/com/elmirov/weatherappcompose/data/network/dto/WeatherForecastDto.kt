package com.elmirov.weatherappcompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherForecastDto(
    @SerializedName("current") val current: WeatherDto,
    @SerializedName("forecast") val forecastDto: ForecastDto,
)