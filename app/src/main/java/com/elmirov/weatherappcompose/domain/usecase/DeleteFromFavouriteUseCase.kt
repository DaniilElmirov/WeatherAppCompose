package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.repository.FavouriteRepository
import javax.inject.Inject

class DeleteFromFavouriteUseCase @Inject constructor(
    private val repository: FavouriteRepository,
) {

    suspend operator fun invoke(cityId: Int) {
        repository.delete(cityId)
    }
}