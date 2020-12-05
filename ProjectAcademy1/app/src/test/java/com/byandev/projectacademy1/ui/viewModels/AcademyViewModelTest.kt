package com.byandev.projectacademy1.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.ui.academy.AcademyViewModel
import com.byandev.projectacademy1.value_object.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
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

    update again :
    AcademyViewModelTest
        - Test terhadap implementasi live data
     */

    private lateinit var viewModel: AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<CourseEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<CourseEntity>

    @Before
    fun setUp() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourse() {
        val dummyCourses = Resource.success(pagedList)
        `when`(dummyCourses.data?.size).thenReturn(5)
        val courses = MutableLiveData<Resource<PagedList<CourseEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourse().value?.data
        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourse().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }

}