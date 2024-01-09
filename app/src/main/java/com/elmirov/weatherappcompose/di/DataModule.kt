package com.elmirov.weatherappcompose.di

import com.elmirov.weatherappcompose.data.network.api.KeyInterceptor
import com.elmirov.weatherappcompose.data.network.api.WeatherApi
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

        @Provides
        fun provideHttpClient(
            keyInterceptor: KeyInterceptor,
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(keyInterceptor)
                .build()

        @Provides
        fun provideRetrofit(
            client: OkHttpClient,
        ): Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        fun provideWeatherApi(retrofit: Retrofit): WeatherApi =
            retrofit.create()
    }
}