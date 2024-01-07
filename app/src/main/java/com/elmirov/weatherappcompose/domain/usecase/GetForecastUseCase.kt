package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.entity.Forecast
import com.elmirov.weatherappcompose.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {

    suspend operator fun invoke(cityId: Int): Forecast = repository.getForecast(cityId)
}