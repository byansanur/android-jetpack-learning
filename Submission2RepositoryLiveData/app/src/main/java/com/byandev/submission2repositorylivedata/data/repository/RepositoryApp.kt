package com.byandev.submission2repositorylivedata.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.*
import com.byandev.submission2repositorylivedata.data.resource.DataSource

open class RepositoryApp(
    private val localRepo: LocalRepo,
    private val remoteRepository: RemoteRepository
) : DataSource {
    override fun getMovie(): LiveData<List<NowPlayingResult>> {
        val movieLists = MutableLiveData<List<NowPlayingResult>>()
        remoteRepository.getMovie(object  : RemoteRepository.GetMovieCallback {
            override fun onResponse(movieResponse: List<NowPlayingResult>) {
                movieLists.postValue(movieResponse)
            }

            override fun onErrorResponse() {
                print("error to get movies")
            }
        })
        return movieLists
    }

    override fun getMovieDetail(movieId: Long): LiveData<MovieDetail> {
        val movieDetail = MutableLiveData<MovieDetail>()
        remoteRepository.getMovieDetail(movieId,object  : RemoteRepository.GetMovieDetailCallback {
            override fun onResponse(movieResponse: MovieDetail) {
                movieDetail.postValue(movieResponse)
            }

            override fun onErrorResponse() {
                print("error to get movies")
            }

        })
        return movieDetail
    }

    override fun getTvShow(): LiveData<List<TvResult>> {
        val tvList = MutableLiveData<List<TvResult>>()
        remoteRepository.getTvShow(object  : RemoteRepository.GetTvShowCallback {
            override fun onResponse(tvShowResponse: List<TvResult>) {
                tvList.postValue(tvShowResponse)
            }

            override fun onErrorResponse() {
                print("error to get movies")
            }
        })
        return tvList
    }

    override fun getTvShowDetail(tvShowId: Long): LiveData<TvDetailResponse> {
        val tvDetail = MutableLiveData<TvDetailResponse>()
        remoteRepository.getTvShowDetail(tvShowId,object  : RemoteRepository.GetTvShowDetailCallback {
            override fun onResponse(tvShowsResponse: TvDetailResponse) {
                tvDetail.postValue(tvShowsResponse)
            }

            override fun onErrorResponse() {
                print("error to get movies")
            }

        })
        return tvDetail
    }

    companion object {
        @Volatile
        private var instance: RepositoryApp? = null
        fun getInstance(localRepo: LocalRepo, remoteRepository: RemoteRepository) : RepositoryApp? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = RepositoryApp(localRepo, remoteRepository)
                    }
                }
            }
            return instance
        }
    }
}