package com.byandev.projectacademy1.injection

import android.content.Context
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.LocalDataSource
import com.byandev.projectacademy1.data.source.local.room.AcademyDatabase
import com.byandev.projectacademy1.data.source.remote.RemoteDataSource
import com.byandev.projectacademy1.utils.AppExecutors
import com.byandev.projectacademy1.utils.JsonHelper

object Injection {

    fun provideRepository(context: Context) : AcademyRepository {
        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}