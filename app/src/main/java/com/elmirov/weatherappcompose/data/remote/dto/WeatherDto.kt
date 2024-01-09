package com.elmirov.weatherappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("last_updated_epoch") val date: Long,
    @SerializedName("temp_c") val tempCelsius: Float,
    @SerializedName("condition") val conditionDto: ConditionDto,
)
