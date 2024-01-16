package com.elmirov.weatherappcompose.presentation.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.elmirov.weatherappcompose.presentation.detail.component.DetailsComponent
import com.elmirov.weatherappcompose.presentation.favourite.component.FavouriteComponent
import com.elmirov.weatherappcompose.presentation.search.component.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Details(val component: DetailsComponent) : Child

        data class Favourite(val component: FavouriteComponent) : Child

        data class Search(val component: SearchComponent) : Child
    }
}