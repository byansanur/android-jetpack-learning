package com.byandev.roomexample.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.byandev.roomexample.databases.Note
import com.byandev.roomexample.repo.Repository

class MainViewModel(application: Application) : ViewModel() {
    private val repository: Repository = Repository(application)
    fun getAllNotes(sort: String): LiveData<PagedList<Note>> {
        return LivePagedListBuilder(repository.getAllNotes(sort), 20).build()
    }
}