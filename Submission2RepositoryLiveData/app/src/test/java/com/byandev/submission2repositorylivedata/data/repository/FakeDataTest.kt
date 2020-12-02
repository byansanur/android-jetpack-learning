package com.byandev.submission2repositorylivedata.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byandev.submission2repositorylivedata.data.repository.remote.*

open class FakeDataTest(
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

    fun getGenre(id: Long) : LiveData<List<GenreDetail>> {
        val genreDetails = MutableLiveData<List<GenreDetail>>()
        remoteRepository.getGenreDetailMovie(id, object : RemoteRepository.GetGenreMovieCallback {
            override fun onResponse(genreDetail: List<GenreDetail>) {
                genreDetails.postValue(genreDetail)
            }

            override fun onErrorResponse() {
                print("error get genre movie")
            }

        })
        return genreDetails
    }

    fun getTvGenre(tvId: Long) : LiveData<List<GenreDetail>> {
        val genreTv = MutableLiveData<List<GenreDetail>>()
        remoteRepository.getGenreDetailTv(tvId, object : RemoteRepository.GetGenreTvCallback {
            override fun onResponse(genreDetail: List<GenreDetail>) {
                genreTv.postValue(genreDetail)
            }

            override fun onErrorResponse() {
                print("error get genre tv show")
            }

        })
        return genreTv
    }

}