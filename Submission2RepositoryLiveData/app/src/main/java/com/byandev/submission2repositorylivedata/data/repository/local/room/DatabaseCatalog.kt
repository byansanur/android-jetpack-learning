package com.byandev.submission2repositorylivedata.data.repository.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.byandev.submission2repositorylivedata.data.repository.local.entity.MovieListResultLocal
import com.byandev.submission2repositorylivedata.data.repository.local.entity.TvListResultLocal

@Database(entities = [MovieListResultLocal::class, TvListResultLocal::class], version = 1, exportSchema = false)
abstract class DatabaseCatalog : RoomDatabase() {

    abstract fun daoData() : DaoData

    companion object {
        @Volatile
        private var INSTANCE: DatabaseCatalog? = null

        fun getInstance(context: Context) : DatabaseCatalog =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseCatalog::class.java,
                    "catalog_movie.id"
                ).build()
            }
    }
}