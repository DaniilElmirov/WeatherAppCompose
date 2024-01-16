package com.elmirov.weatherappcompose.presentation.root.content

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.elmirov.weatherappcompose.presentation.detail.content.DetailsContent
import com.elmirov.weatherappcompose.presentation.favourite.content.FavouriteContent
import com.elmirov.weatherappcompose.presentation.root.component.RootComponent
import com.elmirov.weatherappcompose.presentation.search.content.SearchContent
import com.elmirov.weatherappcompose.presentation.ui.theme.WeatherAppComposeTheme

@Composable
fun RootContent(component: RootComponent) {
    WeatherAppComposeTheme {
        Children(
            stack = component.stack,
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Details -> DetailsContent(component = instance.component)

                is RootComponent.Child.Favourite -> FavouriteContent(component = instance.component)

                is RootComponent.Child.Search -> SearchContent(component = instance.component)
            }
        }
    }
}