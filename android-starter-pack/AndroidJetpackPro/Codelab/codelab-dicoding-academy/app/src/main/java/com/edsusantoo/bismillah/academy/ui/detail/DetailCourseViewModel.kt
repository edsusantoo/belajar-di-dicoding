package com.edsusantoo.bismillah.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository


class DetailCourseViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {
    private var courseId: String? = null

    fun getCourse(): LiveData<CourseEntity?>? {
        return academyRepository?.getCourseWithModules(courseId)
    }

    fun getModules(): LiveData<List<ModuleEntity>>? {
        return academyRepository?.getAllModulesByCourse(courseId)
    }

    fun setCourseId(courseId: String) {
        this.courseId = courseId
    }

    fun getCourseId(): String {
        return courseId!!
    }
}