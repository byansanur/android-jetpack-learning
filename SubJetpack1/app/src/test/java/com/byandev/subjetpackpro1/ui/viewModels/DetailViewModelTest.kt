package com.byandev.subjetpackpro1.ui.viewModels

import com.byandev.subjetpackpro1.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDataMovie()
    private val dummyTvShow = DataDummy.generateDataTvShow()
    private val movieId = dummyMovie[0].movieId
    private val tvId = dummyTvShow[0].tvShowId

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTv(tvId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie[0].movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie[0].movieId, movieEntity.movieId)
        assertEquals(dummyMovie[0].title, movieEntity.title)
        assertEquals(dummyMovie[0].description, movieEntity.description)
        assertEquals(dummyMovie[0].releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie[0].genre, movieEntity.genre)
        assertEquals(dummyMovie[0].imgPath, movieEntity.imgPath)
        assertEquals(dummyMovie[0].url, movieEntity.url)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTv(dummyTvShow[0].tvShowId)
        val tvEntity = viewModel.getTv()
        assertNotNull(tvEntity)
        assertEquals(dummyTvShow[0].tvShowId, tvEntity.tvShowId)
        assertEquals(dummyTvShow[0].title, tvEntity.title)
        assertEquals(dummyTvShow[0].description, tvEntity.description)
        assertEquals(dummyTvShow[0].releaseDate, tvEntity.releaseDate)
        assertEquals(dummyTvShow[0].genre, tvEntity.genre)
        assertEquals(dummyTvShow[0].imgPath, tvEntity.imgPath)
        assertEquals(dummyTvShow[0].url, tvEntity.url)
    }
}