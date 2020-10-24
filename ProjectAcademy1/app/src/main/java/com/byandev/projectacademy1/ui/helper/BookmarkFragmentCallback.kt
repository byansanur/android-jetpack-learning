package com.byandev.projectacademy1.ui.helper


import com.byandev.projectacademy1.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

