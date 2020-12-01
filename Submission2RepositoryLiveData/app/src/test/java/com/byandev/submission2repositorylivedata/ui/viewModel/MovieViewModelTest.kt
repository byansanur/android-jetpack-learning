package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.byandev.submission2repositorylivedata.FakeData
import com.byandev.submission2repositorylivedata.data.repository.DataRepository
import com.byandev.submission2repositorylivedata.data.repository.remote.GenreDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class MovieViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: MovieViewModel? = null
    private var data = mock(DataRepository::class.java)
    private lateinit var movieLs: MovieListResult

    @Mock
    private lateinit var observer: Observer<List<MovieListResult>>


    @Before
    fun setUp() {
        viewModel = MovieViewModel(data)
        movieLs = MovieListResult(
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

    @Test
    fun getMovie() {
        val movieLs = MutableLiveData<List<MovieListResult>>()
        movieLs.value = FakeData.generateDummyRemoteMovie()
        `when`(data.getMovie()).thenReturn(movieLs)
        viewModel?.movie?.observeForever(observer)
        verify(data).getMovie()
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<MovieDetail>()
        movie.value = FakeData.getDummyMovieDetail()
        `when`(data.getMovieDetail(movie.value!!.id.toLong())).thenReturn(movie)
        val observer = mock(Observer::class.java)
        viewModel?.getMovieDetail(movie.value!!.id.toLong())?.observeForever(observer as Observer<in MovieDetail>)
        verify(data).getMovieDetail(movie.value!!.id.toLong())
        assertEquals(
            movie.value?.id,
            movie.value?.id?.let { viewModel?.getMovieDetail(it.toLong())?.value?.id }
        )
        assertEquals(
            movie.value?.title,
            movie.value?.id?.let { viewModel?.getMovieDetail(it.toLong())?.value?.title }
        )
        assertEquals(
            movie.value?.overview,
            movie.value?.id?.let { viewModel?.getMovieDetail(it.toLong())?.value?.overview }
        )
    }

    @Test
    fun getGenre() {
        val genre = MutableLiveData<List<GenreDetail>>()
        genre.value = FakeData.generateGenreMovie(movieLs.id)
        `when`(data.getGenreDetailMovie(movieLs.id)).thenReturn(genre)
        val observer = mock(Observer::class.java)
        viewModel?.getGenres(movieLs.id)?.observeForever(observer as Observer<in List<GenreDetail>>)
        verify(data).getGenreDetailMovie(movieId = movieLs.id)
    }
}