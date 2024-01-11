package com.elmirov.weatherappcompose.data.mapper

import com.elmirov.weatherappcompose.data.remote.dto.WeatherCurrentDto
import com.elmirov.weatherappcompose.data.remote.dto.WeatherDto
import com.elmirov.weatherappcompose.data.remote.dto.WeatherForecastDto
import com.elmirov.weatherappcompose.domain.entity.Forecast
import com.elmirov.weatherappcompose.domain.entity.Weather
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toEntity(): Weather = weatherDto.toEntity()

fun WeatherForecastDto.toEntity(): Forecast = Forecast(
    currentWeather = current.toEntity(),
    upcoming = forecastDto.forecastDay.drop(1).map { dayDto ->
        val dayWeatherDto = dayDto.dayWeatherDto
        Weather(
            tempCelsius = dayWeatherDto.tempCelsius,
            conditionText = dayWeatherDto.conditionDto.text,
            conditionIconUrl = dayWeatherDto.conditionDto.iconUrl.toCorrectIconUrl(),
            date = dayDto.date.toCalendar(),
        )
    }
)

private fun WeatherDto.toEntity(): Weather = Weather(
    tempCelsius = tempCelsius,
    conditionText = conditionDto.text,
    conditionIconUrl = conditionDto.iconUrl.toCorrectIconUrl(),
    date = date.toCalendar(),
)

private fun Long.toCalendar(): Calendar = Calendar.getInstance().apply {
    time = Date(this@toCalendar.inMillis())
}

private fun Long.inMillis(): Long = this * 1000

private fun String.toCorrectIconUrl(): String =
    "https:$this".replace(oldValue = "64x64", newValue = "128x128")