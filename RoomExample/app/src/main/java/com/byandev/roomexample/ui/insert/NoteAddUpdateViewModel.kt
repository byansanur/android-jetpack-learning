package com.byandev.roomexample.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.byandev.roomexample.databases.Note
import com.byandev.roomexample.repo.Repository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {

    private val repository: Repository = Repository(application)

    fun insert(note: Note) {
        repository.insert(note)
    }
    fun update(note: Note) {
        repository.update(note)
    }
    fun delete(note: Note) {
        repository.delete(note)
    }
}