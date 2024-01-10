package com.elmirov.weatherappcompose.data.mapper

import com.elmirov.weatherappcompose.data.local.model.CityDbModel
import com.elmirov.weatherappcompose.data.remote.dto.CityDto
import com.elmirov.weatherappcompose.domain.entity.City

fun City.toDbModel(): CityDbModel = CityDbModel(id, name, country)

fun List<CityDbModel>.toEntities(): List<City> = map { it.toEntity() }

private fun CityDbModel.toEntity(): City = City(id, name, country)

fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }

private fun CityDto.toEntity(): City = City(id, name, country)
