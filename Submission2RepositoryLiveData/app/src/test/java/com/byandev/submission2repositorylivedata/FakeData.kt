package com.byandev.submission2repositorylivedata

import com.byandev.submission2repositorylivedata.data.repository.remote.*

object FakeData {
    fun generateDummyRemoteMovie(): List<MovieListResult> {
        return listOf(
            MovieListResult(
                false,
                "dfsdsff",
                emptyList(),
                1L,
                "en",
                "abcde",
                "asdfghjklqwertyuiopzxcvbnm",
                4.5,
                "fvsdfsdfsd",
                "12-02-2019",
                "abcde",
                false,
                3.0,
                1000
            ),
            MovieListResult(
                false,
                "",
                emptyList(),
                2L,
                "en",
                "abcde",
                "asdfghjklqwertyuiopzxcvbnm",
                4.5,
                "",
                "12-02-2019",
                "",
                false,
                3.0,
                1000
            ),
            MovieListResult(
                false,
                "",
                emptyList(),
                3L,
                "en",
                "abcde",
                "asdfghjklqwertyuiopzxcvbnm",
                4.5,
                "",
                "12-02-2019",
                "",
                false,
                3.0,
                1000
            )
        )
    }

    fun generateDummyTvShow(): List<TvListResult> {
        return listOf(
            TvListResult(
                "",
                "dfsdsff",
                emptyList(),
                1L,
                "name",
                emptyList(),
                "en",
                "sdfsdf",
                "goof",
                5.5,
                "abcde",
                10.0,
                5
            ),
            TvListResult(
                "",
                "dfsdsff",
                emptyList(),
                2L,
                "name",
                emptyList(),
                "en",
                "sdfsdf",
                "goof",
                5.5,
                "abcde",
                10.0,
                5
            ),
            TvListResult(
                "",
                "dfsdsff",
                emptyList(),
                3L,
                "name",
                emptyList(),
                "en",
                "sdfsdf",
                "goof",
                5.5,
                "abcde",
                10.0,
                5
            )
        )
    }

    fun getDummyTvShowDetail() : TvDetailResponse {
        return TvDetailResponse(
            "",
            "dfsdsff",
            emptyList(),
            "fjdsfkj",
            1,
            "fsdff",
            3,
            1,
            "en",
            "abcde",
            10.0,
            "",
            10.0
        )
    }

    fun getDummyMovieDetail() : MovieDetail {
        return MovieDetail(
            "dfsdsff",
            emptyList(),
            "fsdfsd.com",
            1,
            "54534534534",
            "en",
            "abcde",
            "asdfghjklqwertyuiopzxcvbnm",
            4.5,
            "fvsdfsdfsd",
            "12-02-2019",
            1,
            90,
            "coming soon",
            "membaca",
            "abcde",
            3.0
        )
    }

    fun generateGenreMovie(movieId: Long) : List<GenreDetail> {
        val genreDetail = ArrayList<GenreDetail>()
        genreDetail.add(
            GenreDetail(
                movieId.toInt(),
                "action"
            )
        )
        return genreDetail
    }

    fun generateGenreTv(tvId: Long) : List<GenreDetail> {
        val genreDetail = ArrayList<GenreDetail>()
        genreDetail.add(
            GenreDetail(
                tvId.toInt(),
                "action"
            )
        )
        genreDetail.add(
            GenreDetail(
                tvId.toInt(),
                "opera"
            )
        )
        return genreDetail
    }
}