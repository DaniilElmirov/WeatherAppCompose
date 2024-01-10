package com.elmirov.weatherappcompose.data.repository

import com.elmirov.weatherappcompose.data.datasource.FavouriteCitiesLocalDatasource
import com.elmirov.weatherappcompose.data.mapper.toDbModel
import com.elmirov.weatherappcompose.data.mapper.toEntities
import com.elmirov.weatherappcompose.domain.entity.City
import com.elmirov.weatherappcompose.domain.repository.FavouriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val localDatasource: FavouriteCitiesLocalDatasource,
) : FavouriteRepository {

    override val favouriteCities: Flow<List<City>> =
        localDatasource.getAll().map { it.toEntities() }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> =
        localDatasource.observeIsFavourite(cityId)

    override suspend fun add(city: City) {
        localDatasource.add(city.toDbModel())
    }

    override suspend fun delete(cityId: Int) {
        localDatasource.delete(cityId)
    }
}