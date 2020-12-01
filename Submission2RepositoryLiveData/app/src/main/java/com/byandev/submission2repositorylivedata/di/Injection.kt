package com.byandev.submission2repositorylivedata.di

import com.byandev.submission2repositorylivedata.data.repository.DataRepository
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.RemoteRepository

object Injection {
    fun appInject(): DataRepository? {
        val localRepo= LocalRepo()
        val remoteRepository = RemoteRepository.getInstance()
        return DataRepository.getInstance(localRepo, remoteRepository)
    }
}