package com.byandev.submission2repositorylivedata.network

import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResponse
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.data.repository.remote.TvListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEndpoint {

    @GET("movie/popular")
    fun getMovie() : Call<MovieListResponse>

    @GET("tv/popular")
    fun getTv() : Call<TvListResponse>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") tvId: Long
    ) : Call<TvDetailResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Long
    ) : Call<MovieDetail>
}