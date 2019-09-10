package com.edsusantoo.bismillah.academy.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Relation

class CourseWithModule {
    @Embedded
    var mCourse: CourseEntity? = null
    @Relation(parentColumn = "courseId", entityColumn = "courseId")
    var mModules: List<ModuleEntity>? = null
}