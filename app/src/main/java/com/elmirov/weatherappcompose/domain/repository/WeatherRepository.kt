package com.elmirov.weatherappcompose.domain.repository

import com.elmirov.weatherappcompose.domain.entity.Forecast
import com.elmirov.weatherappcompose.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast
}