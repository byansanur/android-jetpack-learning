package com.byandev.projectacademy1.ui.viewModels

import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity
import com.byandev.projectacademy1.ui.detail.DetailCourseViewModel
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

    update :
    DetailCourseViewModelTest:

    Memuat Course:
        - Memanipulasi data ketika pemanggilan data course di kelas repository.
        - Memastikan metode di kelas repository terpanggil.
        - Melakukan pengecekan data course apakah null atau tidak.
        - Membandingkan data course sudah sesuai dengan yang diharapkan atau tidak.

    Memuat Modules:
        - Memanipulasi data ketika pemanggilan data module di kelas repository.
        - Memastikan metode di kelas repository terpanggil.
        - Melakukan pengecekan data module apakah null atau tidak.
        - Melakukan pengecekan jumlah data module apakah sudah sesuai atau belum.


     */

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId))
            .thenReturn(dummyCourse)
        viewModel.setSelectedCourse(dummyCourse.courseId)
        val courseEntity = viewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)
        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity.courseId)
        assertEquals(dummyCourse.deadline, courseEntity.deadline)
        assertEquals(dummyCourse.description, courseEntity.description)
        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
        assertEquals(dummyCourse.title, courseEntity.title)
    }

    @Test
    fun getModule() {
        `when`<ArrayList<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId))
            .thenReturn(DataDummy.generateDummyModules(courseId))
        val moduleEntity = viewModel.getModule()
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntity)
        assertEquals(7, moduleEntity.size.toLong())
    }
}