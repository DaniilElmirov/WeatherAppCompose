package com.elmirov.weatherappcompose.presentation.detail.component

import com.elmirov.weatherappcompose.presentation.detail.store.DetailsStore
import kotlinx.coroutines.flow.StateFlow

interface DetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onClickBack()

    fun onClickChangeFavouriteStatus()
}