package com.elmirov.weatherappcompose.di

import dagger.Component

@Component(
    modules = [
        DataModule::class,
    ]
)
interface AppComponent {
}