package com.elmirov.weatherappcompose.di.module

import android.content.Context
import androidx.room.Room
import com.elmirov.weatherappcompose.data.datasource.FavouriteCitiesLocalDatasource
import com.elmirov.weatherappcompose.data.datasource.FavouriteCitiesLocalDatasourceImpl
import com.elmirov.weatherappcompose.data.datasource.WeatherRemoteDataSource
import com.elmirov.weatherappcompose.data.datasource.WeatherRemoteDataSourceImpl
import com.elmirov.weatherappcompose.data.local.db.FavouriteCitiesDao
import com.elmirov.weatherappcompose.data.local.db.FavouriteDatabase
import com.elmirov.weatherappcompose.data.remote.api.RequestInterceptor
import com.elmirov.weatherappcompose.data.remote.api.WeatherApi
import com.elmirov.weatherappcompose.data.repository.FavouriteRepositoryImpl
import com.elmirov.weatherappcompose.data.repository.SearchRepositoryImpl
import com.elmirov.weatherappcompose.data.repository.WeatherRepositoryImpl
import com.elmirov.weatherappcompose.di.annotation.AppScope
import com.elmirov.weatherappcompose.domain.repository.FavouriteRepository
import com.elmirov.weatherappcompose.domain.repository.SearchRepository
import com.elmirov.weatherappcompose.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module(includes = [DataBindModule::class])
class DataModule {

    private companion object {

        private const val BASE_URL = "https://api.weatherapi.com/v1/"
    }

    @AppScope
    @Provides
    fun provideHttpClient(
        requestInterceptor: RequestInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
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

    @AppScope
    @Provides
    fun provideFavouriteDatabase(context: Context): FavouriteDatabase =
        Room.databaseBuilder(
            context = context,
            klass = FavouriteDatabase::class.java,
            FavouriteDatabase.DATABASE_NAME
        ).build()

    @AppScope
    @Provides
    fun provideFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao =
        database.favouriteCitiesDao()
}

@Module
interface DataBindModule {

    @AppScope
    @Binds
    fun bindFavouriteCitiesLocalDatasource(impl: FavouriteCitiesLocalDatasourceImpl): FavouriteCitiesLocalDatasource

    @AppScope
    @Binds
    fun bindWeatherRemoteDataSource(impl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource

    @AppScope
    @Binds
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @AppScope
    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @AppScope
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}