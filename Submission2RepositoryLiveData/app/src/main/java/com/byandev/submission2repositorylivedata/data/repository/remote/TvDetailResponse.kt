package com.byandev.submission2repositorylivedata.data.repository.remote

data class TvDetailResponse(
    val backdrop_path: String,
    val first_air_date: String,
    val genres: List<GenreDetail>,
    val homepage: String,
    val id: Int,
    val languages: List<String>,
    val name: String,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double
)


