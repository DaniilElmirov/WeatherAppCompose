package com.elmirov.weatherappcompose.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elmirov.weatherappcompose.data.local.model.CityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCitiesDao {

    @Query("SELECT * FROM ${CityDbModel.TABLE_NAME}")
    fun getAll(): Flow<List<CityDbModel>>

    @Query("SELECT EXISTS (SELECT * FROM ${CityDbModel.TABLE_NAME} WHERE id=:cityId LIMIT 1)")
    fun observeIsFavourite(cityId: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(cityDbModel: CityDbModel)

    @Query("DELETE FROM ${CityDbModel.TABLE_NAME} WHERE id=:cityId")
    suspend fun delete(cityId: Int)
}