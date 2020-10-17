package com.byandev.subjetpackpro1.ui.viewModels

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @Before
    fun setUp() {
        viewModel = MoviesViewModel()
    }

    @Test
    fun getMovies() {
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(10, movieEntity.size)
    }

}