package com.elmirov.weatherappcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ConditionDto(
    val text: String,
    @SerializedName("icon") val iconUrl: String,
)
