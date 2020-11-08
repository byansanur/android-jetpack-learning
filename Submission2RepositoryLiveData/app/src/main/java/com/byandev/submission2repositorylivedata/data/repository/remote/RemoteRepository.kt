package com.byandev.submission2repositorylivedata.data.repository.remote

import android.os.Handler
import android.util.Log
import com.byandev.submission2repositorylivedata.network.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteRepository {

    private val apiEndpoint = RetrofitConfig.getClient()

    fun getMovie(getMovieCallBack: GetMovieCallback) {

        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getMovie().enqueue(object : retrofit2.Callback<NowPlayingResponse> {
                override fun onResponse(
                    call: Call<NowPlayingResponse>,
                    response: Response<NowPlayingResponse>
                ) {
                    getMovieCallBack.onResponse(response.body()?.results!!)
                }

                override fun onFailure(call: Call<NowPlayingResponse>, t: Throwable) {
                    getMovieCallBack.onErrorResponse()
                    Log.e("getMovie", "onFailure: getMovie")
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShow(getTvShowCallback: GetTvShowCallback) {
        val responseHandler = Handler()
        responseHandler.postDelayed({
            apiEndpoint.getTv().enqueue(object : Callback<TvResponse> {
                override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                    response.body()?.let { getTvShowCallback.onResponse(response.body()?.results!!) }
                }

                override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                    getTvShowCallback.onErrorResponse()
                    Log.e("getTvShow", "onFailure: getTvShow")
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieDetail(movieId: Long, getMovieDetailCallback: GetMovieDetailCallback) {
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
        }, SERVICE_LATENCY_IN_MILLIS)
    }


    fun getTvShowDetail(tvShow: Long, getTvShowDetailCallback: GetTvShowDetailCallback) {
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
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface GetMovieCallback {
        fun onResponse(movieResponse: List<NowPlayingResult>)
        fun onErrorResponse()
    }

    interface GetTvShowCallback {
        fun onResponse(tvShowResponse: List<TvResult>)
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