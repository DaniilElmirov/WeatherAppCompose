package com.elmirov.weatherappcompose.domain.repository

import com.elmirov.weatherappcompose.domain.entity.City
import kotlinx.coroutines.flow.Flow

interface FavouriteRepository {

    val favouriteCities: Flow<City>

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun addToFavourite(city: City)

    suspend fun deleteFromFavourite(cityId: Int)
}