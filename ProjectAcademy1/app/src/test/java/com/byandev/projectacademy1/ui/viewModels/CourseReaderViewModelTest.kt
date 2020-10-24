package com.byandev.projectacademy1.ui.viewModels

import com.byandev.projectacademy1.data.source.local.entity.ContentEntity
import com.byandev.projectacademy1.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CourseReaderViewModelTest {

    /*
    Scenario Test
    CourseReaderViewModelTest:
        Memuat Modules:
            - Memastikan data module tidak null.
            - Memastikan jumlah data module sesuai dengan yang diharapkan.
        Memuat Module yang dipilih:
            - Memastikan data module tidak null.
            - Memastikan data content tidak null.
            - Memastikan value dari content tidak null.
            - Memastikan data content sesuai dengan yang diharapkan.
     */

    private lateinit var viewModel: CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId


    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel()
        viewModel.setSelectedCourse(courseId)
        viewModel.setSelectedModule(moduleId)

        val dummyModule = dummyModules[0]
        dummyModule.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">"
                +dummyModule.title+"</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, " +
                "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        )
    }

    @Test
    fun getModules() {
        val moduleEntities = viewModel.getModule()
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }

    @Test
    fun getSelectedModule() {
        val moduleEntity = viewModel.getSelectedModule()
        val contentEntity = moduleEntity.contentEntity
        val content = contentEntity?.content
        assertNotNull(moduleEntity)
        assertNotNull(contentEntity)
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)
    }
}