package com.byandev.submission2repositorylivedata

import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult

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
}