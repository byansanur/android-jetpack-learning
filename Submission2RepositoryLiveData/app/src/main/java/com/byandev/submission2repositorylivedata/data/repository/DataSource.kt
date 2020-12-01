package com.byandev.submission2repositorylivedata.data.repository

import androidx.lifecycle.LiveData
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.data.repository.remote.TvListResult

interface DataSource {

    fun getMovie(): LiveData<List<MovieListResult>>

    fun getMovieDetail(movieId: Long) : LiveData<MovieDetail>

    fun getTvShow() : LiveData<List<TvListResult>>

    fun getTvShowDetail(tvShowId: Long) : LiveData<TvDetailResponse>
}