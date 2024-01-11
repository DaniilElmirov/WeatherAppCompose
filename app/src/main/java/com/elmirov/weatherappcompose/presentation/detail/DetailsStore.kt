package com.elmirov.weatherappcompose.presentation.detail

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.domain.entity.Forecast
import com.elmirov.weatherappcompose.domain.usecase.AddToFavouriteUseCase
import com.elmirov.weatherappcompose.domain.usecase.DeleteFromFavouriteUseCase
import com.elmirov.weatherappcompose.domain.usecase.GetForecastUseCase
import com.elmirov.weatherappcompose.domain.usecase.ObserveFavouriteStateUseCase
import com.elmirov.weatherappcompose.presentation.detail.DetailsStore.Intent
import com.elmirov.weatherappcompose.presentation.detail.DetailsStore.Label
import com.elmirov.weatherappcompose.presentation.detail.DetailsStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickBack : Intent

        data object ChangeFavouriteStatus : Intent
    }

    data class State(
        val city: City,
        val isFavourite: Boolean,
        val forecastState: ForecastState,
    ) {
        sealed interface ForecastState {
            data object Initial : ForecastState

            data object Loading : ForecastState

            data object Error : ForecastState

            data class Loaded(val forecast: Forecast) : ForecastState
        }
    }

    sealed interface Label {
        data object ClickBack : Label
    }
}

class DetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getForecastUseCase: GetForecastUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val observeFavouriteStateUseCase: ObserveFavouriteStateUseCase,
) {

    fun create(city: City): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(
                city = city,
                isFavourite = false,
                forecastState = State.ForecastState.Initial,
            ),
            bootstrapper = BootstrapperImpl(city),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class FavouriteStatusChange(val isFavourite: Boolean) : Action

        data object ForecastLoading : Action

        data object ForecastLoadingError : Action

        data class ForecastLoaded(val forecast: Forecast) : Action
    }

    private sealed interface Msg {
        data class FavouriteStatusChange(val isFavourite: Boolean) : Msg

        data object ForecastLoading : Msg

        data object ForecastLoadingError : Msg

        data class ForecastLoaded(val forecast: Forecast) : Msg
    }

    private inner class BootstrapperImpl(
        private val city: City,
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                observeFavouriteStateUseCase(cityId = city.id).collect {
                    dispatch(Action.FavouriteStatusChange(it))
                }
            }

            scope.launch {
                try {
                    dispatch(Action.ForecastLoading)
                    val forecast = getForecastUseCase(cityId = city.id)
                    dispatch(Action.ForecastLoaded(forecast))
                } catch (e: Exception) {
                    dispatch(Action.ForecastLoadingError)
                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.ChangeFavouriteStatus -> {
                    scope.launch {
                        val state = getState()

                        if (state.isFavourite)
                            deleteFromFavouriteUseCase(state.city.id)
                        else
                            addToFavouriteUseCase(state.city)
                    }
                }

                Intent.ClickBack -> publish(Label.ClickBack)
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.FavouriteStatusChange -> dispatch(Msg.FavouriteStatusChange(action.isFavourite))

                is Action.ForecastLoaded -> dispatch(Msg.ForecastLoaded(action.forecast))

                Action.ForecastLoading -> dispatch(Msg.ForecastLoading)

                Action.ForecastLoadingError -> dispatch(Msg.ForecastLoadingError)
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.FavouriteStatusChange -> copy(isFavourite = msg.isFavourite)

                is Msg.ForecastLoaded -> copy(forecastState = State.ForecastState.Loaded(msg.forecast))

                Msg.ForecastLoading -> copy(forecastState = State.ForecastState.Loading)

                Msg.ForecastLoadingError -> copy(forecastState = State.ForecastState.Error)
            }
    }
}
