package com.byandev.subjetpackpro1.ui.viewModels

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvEntity = viewModel.getTvShow()
        assertNotNull(tvEntity)
        assertEquals(10, tvEntity.size)
    }
}