package com.byandev.projectacademy1.ui.viewModels

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class AcademyViewModelTest {

    /*
    Scenario Test
    AcademyViewModelTest:
        - Memuat Courses:
            - Memastikan data course tidak null.
            - Memastikan jumlah data course sesuai dengan yang diharapkan.
     */

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourse() {
        val courseEntities = viewModel.getCourse()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size) // expected data size
    }

}