package com.elmirov.weatherappcompose.di

import dagger.Component

@Component(
    modules = [
        DataModule::class,
    ]
)
class AppComponent {
}