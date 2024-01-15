package com.elmirov.weatherappcompose.presentation.search.component

import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.presentation.search.store.SearchStore
import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun onClickBack()

    fun onClickSearch()

    fun onClickCity(city: City)
}