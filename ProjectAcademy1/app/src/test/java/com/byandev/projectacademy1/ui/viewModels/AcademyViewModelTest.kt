package com.byandev.projectacademy1.ui.viewModels

import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.ui.academy.AcademyViewModel
import com.byandev.projectacademy1.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    /*
    Scenario Test
    AcademyViewModelTest:
        - Memuat Courses:
            - Memastikan data course tidak null.
            - Memastikan jumlah data course sesuai dengan yang diharapkan.

     update :
     AcademyViewModelTest:
        Memuat Courses:
            - Memanipulasi data ketika pemanggilan data course di kelas repository.
            - Memastikan metode di kelas repository terpanggil.
            - Melakukan pengecekan data course apakah null atau tidak.
            - Melakukan pengecekan jumlah data course apakah sudah sesuai atau belum.
     */

    private lateinit var viewModel: AcademyViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourse() {
        `when`<ArrayList<CourseEntity>>(academyRepository.getAllCourse())
            .thenReturn(DataDummy.generateDummyCourse())
        val courseEntities = viewModel.getCourse()
        verify<AcademyRepository>(academyRepository).getAllCourse()
        assertNotNull(courseEntities)
        assertEquals(15, courseEntities.size) // expected data size
    }

}