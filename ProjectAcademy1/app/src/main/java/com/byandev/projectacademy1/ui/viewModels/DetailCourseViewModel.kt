package com.byandev.projectacademy1.ui.viewModels

import androidx.lifecycle.ViewModel
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
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

    fun getCourse() : CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModule() : List<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)
}