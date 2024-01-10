package com.elmirov.weatherappcompose

import android.app.Application
import com.elmirov.weatherappcompose.di.component.DaggerAppComponent

class WeatherApplication : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}