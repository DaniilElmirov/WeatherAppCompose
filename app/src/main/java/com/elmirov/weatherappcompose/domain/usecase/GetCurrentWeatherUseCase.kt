package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.entity.Weather
import com.elmirov.weatherappcompose.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(cityId: Int): Weather = repository.getWeather(cityId)
}