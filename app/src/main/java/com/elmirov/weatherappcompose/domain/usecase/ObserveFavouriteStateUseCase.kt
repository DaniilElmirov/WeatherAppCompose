package com.elmirov.weatherappcompose.domain.usecase

import com.elmirov.weatherappcompose.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository,
) {

    operator fun invoke(cityId: Int): Flow<Boolean> = repository.observeIsFavourite(cityId)
}