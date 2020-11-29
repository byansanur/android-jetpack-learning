package com.byandev.submission2repositorylivedata.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.byandev.submission2repositorylivedata.FakeData
import com.byandev.submission2repositorylivedata.LiveDataTesting
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult
import com.byandev.submission2repositorylivedata.data.repository.remote.RemoteRepository
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.*
import org.mockito.Mockito.*

class RepositoryAppTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localRepo = mock(LocalRepo::class.java)
    private val remoteRepo = mock(RemoteRepository::class.java)
    private val dataFakeDataTest = FakeDataTest(localRepo, remoteRepo)

    private val movieLs = FakeData.generateDummyRemoteMovie()
    private val movieId = movieLs[0].id
    private val movieDetail = FakeData.getDummyMovieDetail()
    private lateinit var movies : MovieListResult

    @Before
    fun setUp() {
        movies = MovieListResult(
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
        )
    }

    @After
    fun tearDown() {

    }


    @Test
    fun getMovie() {
        doAnswer {
            val callback = it.arguments[0] as RemoteRepository.GetMovieCallback
            callback.onResponse(movieLs)
            null
        }.`when`(remoteRepo).getMovie(any(RemoteRepository.GetMovieCallback::class.java))

        val result = LiveDataTesting.getValue(dataFakeDataTest.getMovie())
        verify(remoteRepo, times(1)).getMovie(any(RemoteRepository.GetMovieCallback::class.java))

        assertEquals(movieLs.size, result.size)
    }


}