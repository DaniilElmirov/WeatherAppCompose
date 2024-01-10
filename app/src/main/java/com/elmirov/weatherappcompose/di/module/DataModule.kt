package com.elmirov.weatherappcompose.di.module

import com.elmirov.weatherappcompose.data.remote.api.KeyInterceptor
import com.elmirov.weatherappcompose.data.remote.api.WeatherApi
import com.elmirov.weatherappcompose.di.annotation.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
interface DataModule {

    companion object {

        private const val BASE_URL = "https://api.weatherapi.com/v1/"

        @AppScope
        @Provides
        fun provideHttpClient(
            keyInterceptor: KeyInterceptor,
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(keyInterceptor)
                .build()

        @AppScope
        @Provides
        fun provideRetrofit(
            client: OkHttpClient,
        ): Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @AppScope
        @Provides
        fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
            retrofit.create()
    }
}