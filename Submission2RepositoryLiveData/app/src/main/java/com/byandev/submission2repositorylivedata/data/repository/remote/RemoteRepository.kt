package com.byandev.submission2repositorylivedata.data.repository.remote

import android.os.Handler
import android.util.Log
import com.byandev.submission2repositorylivedata.network.RetrofitConfig
import com.byandev.submission2repositorylivedata.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteRepository {

    private val apiEndpoint = RetrofitConfig.getClient()

    fun getMovie(getMovieCallBack: GetMovieCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getMovie().enqueue(object : retrofit2.Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    getMovieCallBack.onResponse(response.body()?.results!!)
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    getMovieCallBack.onErrorResponse()
                    Log.e("getMovie", "onFailure: getMovie")
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShow(getTvShowCallback: GetTvShowCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getTv().enqueue(object : Callback<TvListResponse> {
                override fun onResponse(call: Call<TvListResponse>, response: Response<TvListResponse>) {
                    response.body()?.let { getTvShowCallback.onResponse(response.body()?.results!!) }
                }

                override fun onFailure(call: Call<TvListResponse>, t: Throwable) {
                    getTvShowCallback.onErrorResponse()
                    Log.e("getTvShow", "onFailure: getTvShow")
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieDetail(movieId: Long, getMovieDetailCallback: GetMovieDetailCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getDetailMovie(movieId).enqueue(object : Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    response.body()?.let { getMovieDetailCallback.onResponse(it) }
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    getMovieDetailCallback.onErrorResponse()
                    Log.e("getMovieDetail", "onFailure: getDetailMovie")
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }


    fun getTvShowDetail(tvShow: Long, getTvShowDetailCallback: GetTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getDetailTv(tvShow).enqueue(object : Callback<TvDetailResponse> {
                override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                    response.body()?.let { getTvShowDetailCallback.onResponse(it) }
                }

                override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                    getTvShowDetailCallback.onErrorResponse()
                    Log.e("getTvShowDetail", "onFailure: getTvShowDetail")
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getGenreDetailMovie(movieId: Long, getGenre: GetGenreCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getDetailMovie(movieId).enqueue(object : Callback<MovieDetail> {
                override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                    response.body()?.let { getGenre.onResponse(it.genres) }
                }

                override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                    getGenre.onErrorResponse()
                    Log.e("getGenreDetailMovie", "onFailure: getGenreDetailMovie")
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getGenreDetailTv(tvId: Long, getGenre: GetGenreCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getDetailTv(tvId).enqueue(object :Callback<TvDetailResponse> {
                override fun onResponse(call: Call<TvDetailResponse>, response: Response<TvDetailResponse>) {
                    response.body()?.let { getGenre.onResponse(it.genres) }
                }

                override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                    getGenre.onErrorResponse()
                    Log.e("getGenreDetailTv", "onFailure: getGenreDetailTv")
                }

            })
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface GetMovieCallback {
        fun onResponse(movieResponse: List<MovieListResult>)
        fun onErrorResponse()
    }

    interface GetTvShowCallback {
        fun onResponse(tvShowResponse: List<TvListResult>)
        fun onErrorResponse()
    }

    interface GetMovieDetailCallback{
        fun onResponse(movieResponse : MovieDetail)
        fun onErrorResponse()
    }

    interface GetTvShowDetailCallback{
        fun onResponse(tvShowsResponse : TvDetailResponse)
        fun onErrorResponse()
    }

    interface GetGenreCallback {
        fun onResponse(genreDetail: List<GenreDetail>)
        fun onErrorResponse()
    }

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository()
            }
            return INSTANCE!!
        }
    }
}