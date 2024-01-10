package com.elmirov.weatherappcompose.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmirov.weatherappcompose.data.local.model.CityDbModel

@Database(entities = [CityDbModel::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "favourite_db"
    }

    abstract fun favouriteCitiesDao(): FavouriteCitiesDao
}