package com.elmirov.weatherappcompose.data.repository

import com.elmirov.weatherappcompose.data.datasource.WeatherRemoteDataSource
import com.elmirov.weatherappcompose.data.mapper.toEntity
import com.elmirov.weatherappcompose.domain.entity.Forecast
import com.elmirov.weatherappcompose.domain.entity.Weather
import com.elmirov.weatherappcompose.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
) : WeatherRepository {

    private companion object {
        private const val PREFIX_CITY_ID = "id:"
    }

    override suspend fun getWeather(cityId: Int): Weather =
        weatherRemoteDataSource.getCurrent("$PREFIX_CITY_ID$cityId").toEntity()

    override suspend fun getForecast(cityId: Int): Forecast =
        weatherRemoteDataSource.getForecast("$PREFIX_CITY_ID$cityId").toEntity()
}