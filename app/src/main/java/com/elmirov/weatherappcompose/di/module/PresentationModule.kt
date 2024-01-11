package com.elmirov.weatherappcompose.di.module

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.elmirov.weatherappcompose.di.annotation.AppScope
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @AppScope
    @Provides
    fun provideStoreFactory(): StoreFactory = DefaultStoreFactory()
}