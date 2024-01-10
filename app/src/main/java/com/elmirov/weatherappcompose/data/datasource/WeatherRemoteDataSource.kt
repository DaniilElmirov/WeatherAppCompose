package com.elmirov.weatherappcompose.data.datasource

import com.elmirov.weatherappcompose.data.remote.api.WeatherApi
import com.elmirov.weatherappcompose.data.remote.dto.CityDto
import com.elmirov.weatherappcompose.data.remote.dto.WeatherCurrentDto
import com.elmirov.weatherappcompose.data.remote.dto.WeatherForecastDto
import javax.inject.Inject

interface WeatherRemoteDataSource {

    suspend fun getCurrent(query: String): WeatherCurrentDto

    suspend fun getForecast(query: String): WeatherForecastDto

    suspend fun searchCity(query: String): List<CityDto>
}

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi,
) : WeatherRemoteDataSource {
    override suspend fun getCurrent(query: String): WeatherCurrentDto =
        weatherApi.getCurrent(query)

    override suspend fun getForecast(query: String): WeatherForecastDto =
        weatherApi.getForecast(query)

    override suspend fun searchCity(query: String): List<CityDto> =
        weatherApi.searchCity(query)
}