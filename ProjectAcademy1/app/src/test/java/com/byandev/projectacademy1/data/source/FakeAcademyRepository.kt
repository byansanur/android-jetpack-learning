package com.byandev.projectacademy1.data.source

import com.byandev.projectacademy1.data.source.local.entity.ContentEntity
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity
import com.byandev.projectacademy1.data.source.remote.RemoteDataSource

// for fake test private constructor and getInstance remove because for easy to test this repository
class FakeAcademyRepository (
    private val remoteDataSource: RemoteDataSource
) : AcademyDataSource {


    override fun getAllCourse(): ArrayList<CourseEntity> {
        val courseResponse = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponse) {
            val course = CourseEntity(
                response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getBookmarkedCourse(): ArrayList<CourseEntity> {
        val courseResponse = remoteDataSource.getAllCourses()
        val courseList = ArrayList<CourseEntity>()
        for (response in courseResponse) {
            val course = CourseEntity(
                response.id,
                response.title,
                response.description,
                response.date,
                false,
                response.imagePath
            )
            courseList.add(course)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponse = remoteDataSource.getAllCourses()
        lateinit var course : CourseEntity
        for (response in courseResponse) {
            if (response.id == courseId) {
                course = CourseEntity(
                    response.id,
                    response.title,
                    response.description,
                    response.date,
                    false,
                    response.imagePath
                )
            }
        }
        return course
    }

    override fun getAllModulesByCourse(courseId: String): ArrayList<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModule(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        for(response in moduleResponses) {
            val course = ModuleEntity(response.moduleId,
                response.courseId,
                response.title,
                response.position,
                false)
            moduleList.add(course)
        }
        return moduleList
    }

    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModule(courseId)
        lateinit var module: ModuleEntity
        for(response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(response.moduleId,
                    response.courseId,
                    response.title,
                    response.position,
                    false)
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }

}