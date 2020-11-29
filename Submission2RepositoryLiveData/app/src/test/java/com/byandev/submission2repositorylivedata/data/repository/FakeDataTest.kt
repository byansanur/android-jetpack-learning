package com.byandev.submission2repositorylivedata.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.*
import com.byandev.submission2repositorylivedata.data.resource.DataSource

open class FakeDataTest(
    private val localRepo: LocalRepo,
    private val remoteRepository: RemoteRepository
) : DataSource {

    override fun getMovie(): LiveData<List<MovieListResult>> {
        val movieList = MutableLiveData<List<MovieListResult>>()
        remoteRepository.getMovie(object : RemoteRepository.GetMovieCallback {
            override fun onResponse(movieResponse: List<MovieListResult>) {
                movieList.postValue(movieResponse)
            }

            override fun onErrorResponse() {
                print("error get movie")
            }

        })
        return movieList
    }

    override fun getMovieDetail(movieId: Long): LiveData<MovieDetail> {
        val movieDetail = MutableLiveData<MovieDetail>()
        remoteRepository.getMovieDetail(movieId, object : RemoteRepository.GetMovieDetailCallback {
            override fun onResponse(movieResponse: MovieDetail) {
                movieDetail.postValue(movieResponse)
            }

            override fun onErrorResponse() {
                print("error get detail movie")
            }

        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<TvListResult>> {
        val tvList = MutableLiveData<List<TvListResult>>()
        remoteRepository.getTvShow(object : RemoteRepository.GetTvShowCallback {
            override fun onResponse(tvShowResponse: List<TvListResult>) {
                tvList.postValue(tvShowResponse)
            }

            override fun onErrorResponse() {
                print("error get tv show")
            }

        })
        return tvList
    }

    override fun getTvShowDetail(tvShowId: Long): LiveData<TvDetailResponse> {
        val tvShowDetail = MutableLiveData<TvDetailResponse>()
        remoteRepository.getTvShowDetail(tvShowId, object : RemoteRepository.GetTvShowDetailCallback {
            override fun onResponse(tvShowsResponse: TvDetailResponse) {
                tvShowDetail.postValue(tvShowsResponse)
            }

            override fun onErrorResponse() {
                print("error get detail tv show")
            }


        })
        return tvShowDetail
    }

}