package com.byandev.projectacademy1.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

//    fun getBookmark() : List<CourseEntity> = DataDummy.generateDummyCourse()
    fun getBookmark() : LiveData<PagedList<CourseEntity>> = academyRepository.getBookmarkedCourses()

    fun setBookmark(courseEntity: CourseEntity) {
        val newState = !courseEntity.bookmarked
        academyRepository.setCourseBookmark(courseEntity, newState)
    }
}