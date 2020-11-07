package com.byandev.submission2repositorylivedata.network

import com.byandev.submission2repositorylivedata.data.model.ResponseDetailMovie
import com.byandev.submission2repositorylivedata.data.model.ResponseDetailTv
import com.byandev.submission2repositorylivedata.data.model.ResponseMovie
import com.byandev.submission2repositorylivedata.data.model.ResponseTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("page") page: Int
    ) : Response<ResponseMovie>

    @GET("tv/popular")
    fun getTvPopular(
        @Query("page") page: Int
    ) : Response<ResponseTv>

    @GET("tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id") tvId: Int
    ) : Response<ResponseDetailTv>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int
    ) : Response<ResponseDetailMovie>
}