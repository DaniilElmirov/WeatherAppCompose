package com.elmirov.weatherappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherCurrentDto(
    @SerializedName("current") val weatherDto: WeatherDto,
)
