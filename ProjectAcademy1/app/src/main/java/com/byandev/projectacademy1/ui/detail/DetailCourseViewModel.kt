package com.byandev.projectacademy1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseWithModule
import com.byandev.projectacademy1.value_object.Resource

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    val courseId = MutableLiveData<String>()

    fun setSelectedCourse(courseId: String) {
        this.courseId.value = courseId
    }

//    fun getCourse() : CourseEntity {
//        lateinit var course: CourseEntity
//        val courseEntities = DataDummy.generateDummyCourse()
//        for (courseEntity in courseEntities) {
//            if (courseEntity.courseId == courseId) {
//                course = courseEntity
//            }
//        }
//
//        return course
//    }
//
//    fun getModule() : List<ModuleEntity> = DataDummy.generateDummyModules(courseId)

    var courseModule: LiveData<Resource<CourseWithModule>> = Transformations.switchMap(courseId) { mCourseId ->
        // Metode Transformations.switchMap digunakan untuk mengambil data setiap kali courseId-nya berubah.
        academyRepository.getCourseWithModules(mCourseId)
    }

    fun setBookmark() {
        val moduleResource = courseModule.value
        if (moduleResource != null) {
            val courseWithModule = moduleResource.data
            if (courseWithModule != null) {
                val courseEntity = courseWithModule.mCourse
                val newState = !courseEntity.bookmarked
                academyRepository.setCourseBookmark(courseEntity, newState)
            }
        }
    }

//    fun getCourse() : LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)
//
//    fun getModule() : LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}