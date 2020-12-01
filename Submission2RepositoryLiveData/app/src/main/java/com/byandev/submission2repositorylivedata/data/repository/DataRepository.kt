package com.byandev.submission2repositorylivedata.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.*

open class DataRepository(
    private val localRepo: LocalRepo,
    private val remoteRepository: RemoteRepository
) : DataSource {
    override fun getMovie(): LiveData<List<MovieListResult>> {
        val movieLists = MutableLiveData<List<MovieListResult>>()
        remoteRepository.getMovie(object  : RemoteRepository.GetMovieCallback {
            override fun onResponse(movieResponse: List<MovieListResult>) {
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

    override fun getTvShow(): LiveData<List<TvListResult>> {
        val tvList = MutableLiveData<List<TvListResult>>()
        remoteRepository.getTvShow(object  : RemoteRepository.GetTvShowCallback {
            override fun onResponse(tvShowResponse: List<TvListResult>) {
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

    fun getGenreDetailMovie(movieId: Long): LiveData<List<GenreDetail>> {
        val genre = MutableLiveData<List<GenreDetail>>()
        remoteRepository.getGenreDetailMovie(movieId, object : RemoteRepository.GetGenreMovieCallback {
            override fun onResponse(genreDetail: List<GenreDetail>) {
                genre.postValue(genreDetail)
            }

            override fun onErrorResponse() {
                print("error to get genre movie")
            }

        })
        return genre
    }

    fun getGenreDetailTv(tvId:Long) : LiveData<List<GenreDetail>> {
        val genre = MutableLiveData<List<GenreDetail>>()
        remoteRepository.getGenreDetailTv(tvId, object : RemoteRepository.GetGenreTvCallback {
            override fun onResponse(genreDetail: List<GenreDetail>) {
                genre.postValue(genreDetail)
            }

            override fun onErrorResponse() {
                print("error to get genre tv")
            }

        })
        return genre
    }

    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(localRepo: LocalRepo, remoteRepository: RemoteRepository) : DataRepository? {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DataRepository(localRepo, remoteRepository)
                    }
                }
            }
            return instance
        }
    }
}