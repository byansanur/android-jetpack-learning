package com.byandev.projectacademy1.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseWithModule (
    @Embedded
    var mCourse: CourseEntity,
    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var mModules: List<ModuleEntity>
)