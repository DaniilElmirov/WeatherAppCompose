package com.elmirov.weatherappcompose.presentation.search.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.extension.componentScope
import com.elmirov.weatherappcompose.presentation.search.store.OpenReason
import com.elmirov.weatherappcompose.presentation.search.store.SearchStore
import com.elmirov.weatherappcompose.presentation.search.store.SearchStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultSearchComponent @Inject constructor(
    private val openReason: OpenReason,
    private val searchStoreFactory: SearchStoreFactory,
    private val onBackClicked: () -> Unit,
    private val onForecastForCityRequested: (City) -> Unit,
    private val onCitySavedToFavouriteClicked: () -> Unit,
    componentContext: ComponentContext,
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { searchStoreFactory.create(openReason) }

    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    SearchStore.Label.ClickBack -> onBackClicked()

                    is SearchStore.Label.OpenForecast -> onForecastForCityRequested(it.city)

                    SearchStore.Label.SavedToFavourite -> onCitySavedToFavouriteClicked()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State> = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onClickCity(city: City) {
        store.accept(SearchStore.Intent.ClickCity(city))
    }
}