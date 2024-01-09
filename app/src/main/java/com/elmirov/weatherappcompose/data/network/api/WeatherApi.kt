package com.elmirov.weatherappcompose.data.network.api

import com.elmirov.weatherappcompose.data.network.dto.CityDto
import com.elmirov.weatherappcompose.data.network.dto.WeatherCurrentDto
import com.elmirov.weatherappcompose.data.network.dto.WeatherForecastDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json?key=")
    suspend fun getCurrent(
        @Query("q") query: String,
    ): WeatherCurrentDto

    @GET("forecast.json?key=")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4,
    ): WeatherForecastDto

    @GET("search.json?key=")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<CityDto>
}