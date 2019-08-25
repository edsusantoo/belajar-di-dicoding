package com.edsusantoo.bismillah.academy.data.source

import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity


interface AcademyDataSource {

    fun getAllCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String?): CourseEntity?

    fun getAllModulesByCourse(courseId: String?): List<ModuleEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getContent(courseId: String?, moduleId: String?): ModuleEntity?
}
