package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.byandev.submission2repositorylivedata.FakeData
import com.byandev.submission2repositorylivedata.data.repository.DataRepository
import com.byandev.submission2repositorylivedata.data.repository.remote.*
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

@Suppress("UNCHECKED_CAST")
class TvViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: TvViewModel? = null
    private var data = Mockito.mock(DataRepository::class.java)
    private lateinit var tvLs: TvListResult

    @Mock
    private lateinit var observer: Observer<List<TvListResult>>

    @Before
    fun setUp() {
        viewModel = TvViewModel(data)
        tvLs = TvListResult(
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
        )
    }

    @Test
    fun getTvShow() {
        val tvLs = MutableLiveData<List<TvListResult>>()
        tvLs.value = FakeData.generateDummyTvShow()
        Mockito.`when`(data.getTvShow()).thenReturn(tvLs)
        viewModel?.tv?.observeForever(observer)
        verify(data).getTvShow()
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<TvDetailResponse>()
        tvShow.value = FakeData.getDummyTvShowDetail()
        Mockito.`when`(data.getTvShowDetail(tvShow.value!!.id.toLong())).thenReturn(tvShow)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getTvDetail(tvShow.value!!.id.toLong())?.observeForever(observer as Observer<in TvDetailResponse>)
        verify(data).getTvShowDetail(tvShow.value!!.id.toLong())
        assertEquals(
            tvShow.value?.id,
            tvShow.value?.id?.let { viewModel?.getTvDetail(it.toLong())?.value?.id }
        )
        assertEquals(
            tvShow.value?.name,
            tvShow.value?.id?.let { viewModel?.getTvDetail(it.toLong())?.value?.name }
        )
        assertEquals(
            tvShow.value?.overview,
            tvShow.value?.id?.let { viewModel?.getTvDetail(it.toLong())?.value?.overview }
        )
    }

    @Test
    fun getGenre() {
        val genre = MutableLiveData<List<GenreDetail>>()
        genre.value = FakeData.generateGenreTv(tvLs.id)
        Mockito.`when`(data.getGenreDetailTv(tvLs.id)).thenReturn(genre)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getGenresTv(tvLs.id)?.observeForever(observer as Observer<in List<GenreDetail>>)
        verify(data).getGenreDetailTv(tvLs.id)
    }
}