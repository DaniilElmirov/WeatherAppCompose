package com.elmirov.weatherappcompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherCurrentDto(
    @SerializedName("current") val weatherDto: WeatherDto,
)
