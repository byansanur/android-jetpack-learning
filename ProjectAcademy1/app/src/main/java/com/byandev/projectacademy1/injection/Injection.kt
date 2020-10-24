package com.byandev.projectacademy1.injection

import android.content.Context
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.remote.RemoteDataSource
import com.byandev.projectacademy1.utils.helper.JsonHelper

object Injection {

    fun provideRepository(context: Context) : AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}