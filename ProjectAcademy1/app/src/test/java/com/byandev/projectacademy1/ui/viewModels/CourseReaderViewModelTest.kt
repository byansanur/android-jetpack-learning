package com.byandev.projectacademy1.ui.viewModels

import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.ContentEntity
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity
import com.byandev.projectacademy1.ui.reader.CourseReaderViewModel
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

    update :
    CourseReaderViewModelTest:

    Memuat Modules:
        - Memanipulasi data ketika pemanggilan data module di kelas repository.
        - Memastikan metode di kelas repository terpanggil.
        - Melakukan pengecekan data module apakah null atau tidak.
        - Melakukan pengecekan jumlah data module apakah sudah sesuai atau belum.

    Memuat Module yang dipilih:
        - Memanipulasi data ketika pemanggilan data content di kelas repository.
        - Memastikan metode di kelas repository terpanggil.
        - Melakukan pengecekan data content apakah null atau tidak.
        - Membandingkan data content sudah sesuai dengan yang diharapkan atau tidak.
     */

    private lateinit var viewModel: CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourse()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @Mock
    private lateinit var academyRepository: AcademyRepository


    @Before
    fun setUp() {
        viewModel = CourseReaderViewModel(academyRepository)
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
        `when`<ArrayList<ModuleEntity>>(academyRepository.getAllModulesByCourse(courseId))
            .thenReturn(dummyModules)
        val moduleEntities = viewModel.getModule()
        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size.toLong())
    }

    @Test
    fun getSelectedModule() {
        `when`(academyRepository.getContent(courseId,moduleId))
            .thenReturn(dummyModules[0])
        val moduleEntity = viewModel.getSelectedModule()
        val contentEntity = moduleEntity.contentEntity
        val content = contentEntity?.content
        verify(academyRepository).getContent(courseId, moduleId)
        assertNotNull(moduleEntity)
        assertNotNull(contentEntity)
        assertNotNull(content)
        assertEquals(content, dummyModules[0].contentEntity?.content)
    }
}