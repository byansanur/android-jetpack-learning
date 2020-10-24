package com.byandev.projectacademy1.data.source

import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity

interface AcademyDataSource {

    fun getAllCourse() : List<CourseEntity>

    fun getBookmarkedCourse() : List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}