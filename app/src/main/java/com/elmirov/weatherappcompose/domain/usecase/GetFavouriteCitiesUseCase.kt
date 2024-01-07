package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteCitiesUseCase @Inject constructor(
    private val repository: FavouriteRepository,
) {

    operator fun invoke(): Flow<City> = repository.favouriteCities
}