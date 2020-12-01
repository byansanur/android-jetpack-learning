package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.byandev.submission2repositorylivedata.data.repository.DataRepository
import com.byandev.submission2repositorylivedata.data.repository.remote.GenreDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.data.repository.remote.TvListResult

class TvViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {
    val tv : LiveData<List<TvListResult>> = dataRepository.getTvShow()
    fun getTvDetail(tvId: Long) : LiveData<TvDetailResponse> = dataRepository.getTvShowDetail(tvId)

    fun getGenresTv(tvId: Long) : LiveData<List<GenreDetail>> = dataRepository.getGenreDetailTv(tvId)
}