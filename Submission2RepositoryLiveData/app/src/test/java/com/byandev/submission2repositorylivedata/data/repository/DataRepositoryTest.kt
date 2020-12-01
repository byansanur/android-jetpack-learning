@file:Suppress("DEPRECATION")

package com.byandev.submission2repositorylivedata.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.byandev.submission2repositorylivedata.FakeData
import com.byandev.submission2repositorylivedata.utils.LiveDataTesting
import com.byandev.submission2repositorylivedata.data.repository.local.LocalRepo
import com.byandev.submission2repositorylivedata.data.repository.remote.RemoteRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localRepo = mock(LocalRepo::class.java)
    private val remoteRepo = mock(RemoteRepository::class.java)
    private val dataFakeDataTest = FakeDataTest(localRepo, remoteRepo)

    private val movieLs = FakeData.generateDummyRemoteMovie()
    private val movieId = movieLs[0].id
    private val movieDetail = FakeData.getDummyMovieDetail()
    private val movieGenre = FakeData.generateGenreMovie(movieId)

    private val tvShowLs = FakeData.generateDummyTvShow()
    private val tvShowId = tvShowLs[0].id
    private val tvShowDetail = FakeData.getDummyTvShowDetail()
    private val tvGenre = FakeData.generateGenreTv(tvShowId)

    @Test
    fun getMovie() {
        doAnswer {invocation ->
            (invocation.arguments[0] as RemoteRepository.GetMovieCallback)
                .onResponse(movieLs)
            null
        }.`when`(remoteRepo).getMovie(any())

        val result = LiveDataTesting.getValue(dataFakeDataTest.getMovie())
        verify(remoteRepo).getMovie(any())

        assertNotNull(result)
        assertEquals(movieLs.size, result.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteRepository.GetMovieDetailCallback)
                .onResponse(movieDetail)
            null
        }.`when`(remoteRepo).getMovieDetail(eq(movieId), any())

    }

    @Test
    fun getGenreMovie() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteRepository.GetGenreMovieCallback)
                .onResponse(movieGenre)
            null
        }.`when`(remoteRepo).getGenreDetailMovie(eq(movieId), any())

        val resultGenreDetail = LiveDataTesting.getValue(dataFakeDataTest.getGenre(movieId))
        verify(remoteRepo).getGenreDetailMovie(eq(movieId), any())

        assertNotNull(resultGenreDetail)
        assertEquals(movieGenre.size, resultGenreDetail.size)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteRepository.GetTvShowCallback)
                .onResponse(tvShowLs)
            null
        }.`when`(remoteRepo).getTvShow(any())

        val resultTvShow = LiveDataTesting.getValue(dataFakeDataTest.getTvShow())
        verify(remoteRepo).getTvShow(any())

        assertNotNull(resultTvShow)
        assertEquals(tvShowLs.size, resultTvShow.size)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteRepository.GetTvShowDetailCallback)
                .onResponse(tvShowDetail)
            null
        }.`when`(remoteRepo).getTvShowDetail(eq(tvShowId), any())

    }

    @Test
    fun getGenreTv() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteRepository.GetGenreTvCallback)
                .onResponse(tvGenre)
            null
        }.`when`(remoteRepo).getGenreDetailTv(eq(tvShowId), any())

        val resultGenreDetail = LiveDataTesting.getValue(dataFakeDataTest.getTvGenre(tvShowId))
        verify(remoteRepo).getGenreDetailTv(eq(tvShowId), any())

        assertNotNull(resultGenreDetail)
        assertEquals(tvGenre.size, resultGenreDetail.size)
    }


}