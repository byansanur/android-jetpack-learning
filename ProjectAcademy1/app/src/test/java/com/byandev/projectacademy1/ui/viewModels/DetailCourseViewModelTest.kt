package com.byandev.projectacademy1.ui.viewModels

import com.byandev.projectacademy1.utils.DataDummy
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class DetailCourseViewModelTest {

    /*
    Scenario
    DetailCourseViewModelTest:
        Memuat Course:
            - Memastikan data course tidak null.
            - Memastikan data course sesuai dengan yang diharapkan.
        Memuat Modules:
            - Memastikan data module tidak null.
            - Memastikan jumlah data module sesuai dengan yang diharapkan.
     */

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel()
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        viewModel.setSelectedCourse(dummyCourse.courseId)
        val courseEntity = viewModel.getCourse()
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModule() {
        val moduleEntity = viewModel.getModule()
        assertNotNull(moduleEntity)
        assertEquals(7, moduleEntity.size.toLong())
    }
}