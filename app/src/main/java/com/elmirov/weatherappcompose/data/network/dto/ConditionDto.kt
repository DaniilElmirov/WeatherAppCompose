package com.elmirov.weatherappcompose.data.network.dto

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    val text: String,
    @SerializedName("icon") val iconUrl: String,
)
