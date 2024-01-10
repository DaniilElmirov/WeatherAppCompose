package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository,
) {

    suspend operator fun invoke(city: City) {
        repository.add(city)
    }
}