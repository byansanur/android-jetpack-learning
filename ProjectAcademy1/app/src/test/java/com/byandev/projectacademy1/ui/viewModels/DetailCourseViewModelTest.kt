package com.byandev.projectacademy1.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseWithModule
import com.byandev.projectacademy1.ui.detail.DetailCourseViewModel
import com.byandev.projectacademy1.utils.DataDummy
import com.byandev.projectacademy1.value_object.Resource
import org.junit.Before
import org.junit.Rule
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
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

//    @Mock
//    private lateinit var courseObserver: Observer<CourseEntity>
//    @Mock
//    private lateinit var modulesObserver: Observer<List<ModuleEntity>>

    @Mock
    private lateinit var observer: Observer<Resource<CourseWithModule>>

    @Before
    fun setUp() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourseWithModule() {
        val dummyCourseWithModule = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true))
        val course = MutableLiveData<Resource<CourseWithModule>>()
        course.value = dummyCourseWithModule
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
        viewModel.courseModule.observeForever(observer)
        verify(observer).onChanged(dummyCourseWithModule)
    }

//    @Test
//    fun getCourse() {
//        val course = MutableLiveData<CourseEntity>()
//        course.value = dummyCourse
//
//        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
//        val courseEntity = viewModel.getCourse().value as CourseEntity
//        verify(academyRepository).getCourseWithModules(courseId)
//        assertNotNull(courseEntity)
//        assertEquals(dummyCourse.courseId, courseEntity.courseId)
//        assertEquals(dummyCourse.deadline, courseEntity.deadline)
//        assertEquals(dummyCourse.description, courseEntity.description)
//        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
//        assertEquals(dummyCourse.title, courseEntity.title)
//
//        viewModel.getCourse().observeForever(courseObserver)
//        verify(courseObserver).onChanged(dummyCourse)
//    }
//
//    @Test
//    fun getModule() {
//        val module = MutableLiveData<List<ModuleEntity>>()
//        module.value = dummyModules
//
//        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(module)
//        val moduleEntities = viewModel.getModule().value
//        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
//        assertNotNull(moduleEntities)
//        assertEquals(7, moduleEntities?.size)
//
//        viewModel.getModule().observeForever(modulesObserver)
//        verify(modulesObserver).onChanged(dummyModules)
//    }

}