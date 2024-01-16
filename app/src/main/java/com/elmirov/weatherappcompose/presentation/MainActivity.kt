package com.elmirov.weatherappcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import com.elmirov.weatherappcompose.WeatherApplication
import com.elmirov.weatherappcompose.presentation.root.component.DefaultRootComponent
import com.elmirov.weatherappcompose.presentation.root.content.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WeatherApplication).component.inject(this)
        super.onCreate(savedInstanceState)

        val root = rootComponentFactory.create(componentContext = defaultComponentContext())

        setContent {
            RootContent(component = root)
        }
    }
}