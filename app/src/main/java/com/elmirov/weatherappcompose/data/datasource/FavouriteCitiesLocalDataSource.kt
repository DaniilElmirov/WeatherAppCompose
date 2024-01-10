package com.elmirov.weatherappcompose.data.datasource

import com.elmirov.weatherappcompose.data.local.db.FavouriteCitiesDao
import com.elmirov.weatherappcompose.data.local.model.CityDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FavouriteCitiesLocalDatasource {
    fun getAll(): Flow<List<CityDbModel>>

    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    suspend fun add(cityDbModel: CityDbModel)

    suspend fun delete(cityId: Int)
}

class FavouriteCitiesLocalDatasourceImpl @Inject constructor(
    private val favouriteCitiesDao: FavouriteCitiesDao,
) : FavouriteCitiesLocalDatasource {
    override fun getAll(): Flow<List<CityDbModel>> =
        favouriteCitiesDao.getAll()

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> =
        favouriteCitiesDao.observeIsFavourite(cityId)

    override suspend fun add(cityDbModel: CityDbModel) {
        favouriteCitiesDao.add(cityDbModel)
    }

    override suspend fun delete(cityId: Int) {
        favouriteCitiesDao.delete(cityId)
    }
}