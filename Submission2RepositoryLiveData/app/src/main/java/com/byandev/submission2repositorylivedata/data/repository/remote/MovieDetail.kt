package com.byandev.submission2repositorylivedata.data.repository.remote

data class MovieDetail(
    val backdrop_path: String,
    val genres: List<GenreDetail>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double
)