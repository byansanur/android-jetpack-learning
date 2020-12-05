package com.byandev.roomexample.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class DatabaseRoom : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseRoom? = null

        @JvmStatic
        fun getDatabase(context: Context): DatabaseRoom {
            if (INSTANCE == null) {
                synchronized(DatabaseRoom::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseRoom::class.java,
                        "db_name"
                    ).build()
                }
            }
            return INSTANCE as DatabaseRoom
        }
    }
}