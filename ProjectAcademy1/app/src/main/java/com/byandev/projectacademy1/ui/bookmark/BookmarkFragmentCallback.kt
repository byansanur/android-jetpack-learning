package com.byandev.projectacademy1.ui.bookmark


import com.byandev.projectacademy1.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}

