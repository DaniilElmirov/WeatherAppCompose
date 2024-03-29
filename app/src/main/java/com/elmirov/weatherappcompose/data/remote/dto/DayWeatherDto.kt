package com.elmirov.weatherappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DayWeatherDto(
    @SerializedName("avgtemp_c") val tempCelsius: Float,
    @SerializedName("condition") val conditionDto: ConditionDto,
)
