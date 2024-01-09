package com.elmirov.weatherappcompose.data.remote.api

import com.elmirov.weatherappcompose.data.remote.dto.CityDto
import com.elmirov.weatherappcompose.data.remote.dto.WeatherCurrentDto
import com.elmirov.weatherappcompose.data.remote.dto.WeatherForecastDto
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