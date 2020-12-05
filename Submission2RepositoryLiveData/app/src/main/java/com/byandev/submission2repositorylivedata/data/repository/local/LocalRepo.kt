package com.byandev.submission2repositorylivedata.data.repository.local

import com.byandev.submission2repositorylivedata.data.repository.local.room.DaoData

open class LocalRepo private constructor(private val daoData: DaoData) {

    companion object {
        private var INSTANCE: LocalRepo? = null

        fun getInstance(daoData: DaoData) : LocalRepo =
            INSTANCE ?: LocalRepo(daoData)
    }

}