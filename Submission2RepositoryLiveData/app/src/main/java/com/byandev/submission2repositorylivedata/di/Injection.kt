package com.byandev.submission2repositorylivedata.di

import com.byandev.submission2repositorylivedata.data.repository.RepositoryApp
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.RemoteRepository

object Injection {
    fun appInject(): RepositoryApp? {
        val localRepo= LocalRepo()
        val remoteRepository = RemoteRepository.getInstance()
        return RepositoryApp.getInstance(localRepo, remoteRepository)
    }
}