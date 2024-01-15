package com.elmirov.weatherappcompose.presentation.favourite.component

import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.presentation.favourite.store.FavouriteStore
import kotlinx.coroutines.flow.StateFlow

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onClickSearch()

    fun onClickAddToFavourite()

    fun onCityItemClick(city: City)
}