package com.byandev.submission2repositorylivedata.di

import com.byandev.submission2repositorylivedata.data.repository.DataRepository
import com.byandev.submission2repositorylivedata.data.repository.remote.RemoteRepository

object Injection {
    fun appInject(): DataRepository? {
        val remoteRepository = RemoteRepository.getInstance()
        return DataRepository.getInstance(remoteRepository)
    }
}