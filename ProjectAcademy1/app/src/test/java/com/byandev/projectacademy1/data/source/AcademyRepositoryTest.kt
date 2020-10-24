package com.byandev.projectacademy1.data.source

import com.byandev.projectacademy1.data.source.remote.RemoteDataSource
import com.byandev.projectacademy1.data.source.remote.response.ContentResponse
import com.byandev.projectacademy1.data.source.remote.response.CourseResponse
import com.byandev.projectacademy1.data.source.remote.response.ModuleResponse
import com.byandev.projectacademy1.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AcademyRepositoryTest {

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourse()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourse() {
        `when`< List<CourseResponse>>(remote.getAllCourses())
            .thenReturn(courseResponse)
        val courseEntity = academyRepository.getAllCourse()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntity)
        assertEquals(courseResponse.size.toLong(), courseEntity.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = academyRepository.getBookmarkedCourse()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        `when`<List<ModuleResponse>>(remote.getModule(courseId)).thenReturn(moduleResponse)
        `when`<ContentResponse>(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify<RemoteDataSource>(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponse[0].title, resultCourse.title)
    }
}