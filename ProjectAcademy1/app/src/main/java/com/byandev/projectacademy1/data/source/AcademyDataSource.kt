package com.byandev.projectacademy1.data.source

import androidx.lifecycle.LiveData
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity

interface AcademyDataSource {

    fun getAllCourse() : LiveData<List<CourseEntity>>

    fun getBookmarkedCourse() : LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>
}