package com.byandev.roomexample.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

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
                    ).addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            add()
                        }
                    }).build()
                }
            }
            return INSTANCE as DatabaseRoom
        }

        fun add() {
            Executors.newSingleThreadExecutor().execute {
                val list: MutableList<Note> = ArrayList()
                for (i in 0..9) {
                    val dummyNote = Note()
                    dummyNote.title = "Tugas $i"
                    dummyNote.description = "Belajar Modul $i"
                    dummyNote.date= "2019/09/09 09:09:0$i"
                    list.add(dummyNote)
                }
                INSTANCE?.noteDao()?.insertAll(list)
            }
        }
    }
}