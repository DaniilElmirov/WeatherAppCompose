package com.elmirov.weatherappcompose.di.component

import android.content.Context
import com.elmirov.weatherappcompose.WeatherApplication
import com.elmirov.weatherappcompose.di.annotation.AppScope
import com.elmirov.weatherappcompose.di.module.DataModule
import com.elmirov.weatherappcompose.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        DataModule::class,
    ]
)
interface AppComponent {

    fun inject(application: WeatherApplication)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }
}