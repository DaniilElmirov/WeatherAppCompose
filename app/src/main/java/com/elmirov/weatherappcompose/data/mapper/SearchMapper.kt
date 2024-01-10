package com.elmirov.weatherappcompose.data.mapper

import com.elmirov.weatherappcompose.data.remote.dto.CityDto
import com.elmirov.weatherappcompose.domain.entity.City

fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }

private fun CityDto.toEntity(): City = City(id, name, country)