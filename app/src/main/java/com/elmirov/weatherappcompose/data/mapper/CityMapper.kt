package com.elmirov.weatherappcompose.data.mapper

import com.elmirov.weatherappcompose.data.local.model.CityDbModel
import com.elmirov.weatherappcompose.domain.entity.City

fun City.toDbModel(): CityDbModel = CityDbModel(id, name, country)

fun List<CityDbModel>.toEntities(): List<City> = map { it.toEntity() }

private fun CityDbModel.toEntity(): City = City(id, name, country)
