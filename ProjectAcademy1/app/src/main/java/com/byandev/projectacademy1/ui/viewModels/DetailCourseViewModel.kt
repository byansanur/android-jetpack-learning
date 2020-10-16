package com.byandev.projectacademy1.ui.viewModels

import androidx.lifecycle.ViewModel
import com.byandev.projectacademy1.data.CourseEntity
import com.byandev.projectacademy1.data.ModuleEntity
import com.byandev.projectacademy1.utils.DataDummy

class DetailCourseViewModel : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse() : CourseEntity {
        lateinit var course: CourseEntity
        val courseEntities = DataDummy.generateDummyCourse()
        for (courseEntity in courseEntities) {
            if (courseEntity.courseId == courseId) {
                course = courseEntity
            }
        }

        return course
    }

    fun getModule() : List<ModuleEntity> = DataDummy.generateDummyModules(courseId)
}