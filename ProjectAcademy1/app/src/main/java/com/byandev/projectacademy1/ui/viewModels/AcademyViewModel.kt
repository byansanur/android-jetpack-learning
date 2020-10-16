package com.byandev.projectacademy1.ui.viewModels

import androidx.lifecycle.ViewModel
import com.byandev.projectacademy1.data.CourseEntity
import com.byandev.projectacademy1.utils.DataDummy

class AcademyViewModel : ViewModel() {

    fun getCourse() : List<CourseEntity> = DataDummy.generateDummyCourse()
}