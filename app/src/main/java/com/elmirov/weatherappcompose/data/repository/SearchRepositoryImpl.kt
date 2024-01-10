package com.elmirov.weatherappcompose.data.repository

import com.elmirov.weatherappcompose.data.datasource.WeatherRemoteDataSource
import com.elmirov.weatherappcompose.data.mapper.toEntities
import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
) : SearchRepository {

    override suspend fun search(query: String): List<City> =
        weatherRemoteDataSource.searchCity(query).toEntities()
}