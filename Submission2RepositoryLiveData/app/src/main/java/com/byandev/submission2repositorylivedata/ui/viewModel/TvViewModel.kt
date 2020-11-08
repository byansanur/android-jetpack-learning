package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.byandev.submission2repositorylivedata.data.repository.RepositoryApp
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.data.repository.remote.TvResult

class TvViewModel(
    private val repositoryApp: RepositoryApp
) : ViewModel() {
    val tv : LiveData<List<TvResult>> = repositoryApp.getTvShow()
    fun getTvDetail(tvId: Long) : LiveData<TvDetailResponse> = repositoryApp.getTvShowDetail(tvId)
}