package com.byandev.projectacademy1.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.value_object.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

//    fun getCourse() : List<CourseEntity> = DataDummy.generateDummyCourse()
    fun getCourse() : LiveData<Resource<List<CourseEntity>>> = academyRepository.getAllCourses()
}