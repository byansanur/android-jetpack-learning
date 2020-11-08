package com.byandev.submission2repositorylivedata.data.resource

import androidx.lifecycle.LiveData
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.NowPlayingResult
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.data.repository.remote.TvResult

interface DataSource {

    fun getMovie(): LiveData<List<NowPlayingResult>>

    fun getMovieDetail(movieId: Long) : LiveData<MovieDetail>

    fun getTvShow() : LiveData<List<TvResult>>

    fun getTvShowDetail(tvShowId: Long) : LiveData<TvDetailResponse>
}