package com.elmirov.weatherappcompose.domain.repository

import com.elmirov.weatherappcompose.domain.entity.City
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteCities: Flow<List<City>>

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun add(city: City)

    suspend fun delete(cityId: Int)
}