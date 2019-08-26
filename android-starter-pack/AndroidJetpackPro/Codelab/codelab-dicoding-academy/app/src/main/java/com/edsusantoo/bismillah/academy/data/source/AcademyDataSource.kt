package com.edsusantoo.bismillah.academy.data.source

import androidx.lifecycle.LiveData
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity


interface AcademyDataSource {

    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String?): LiveData<CourseEntity?>

    fun getAllModulesByCourse(courseId: String?): LiveData<List<ModuleEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getContent(courseId: String?, moduleId: String?): LiveData<ModuleEntity?>
}
