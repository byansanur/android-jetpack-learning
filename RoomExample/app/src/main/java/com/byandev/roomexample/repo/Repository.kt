package com.byandev.roomexample.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.byandev.roomexample.databases.DatabaseRoom
import com.byandev.roomexample.databases.Note
import com.byandev.roomexample.databases.NoteDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository(application: Application) {
    private val noteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = DatabaseRoom.getDatabase(application)
        noteDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = noteDao.getAllNotes()
    fun insert(note: Note) {
        executorService.execute { noteDao.insert(note) }
    }
    fun delete(note: Note) {
        executorService.execute { noteDao.delete(note) }
    }
    fun update(note: Note) {
        executorService.execute { noteDao.update(note) }
    }
}