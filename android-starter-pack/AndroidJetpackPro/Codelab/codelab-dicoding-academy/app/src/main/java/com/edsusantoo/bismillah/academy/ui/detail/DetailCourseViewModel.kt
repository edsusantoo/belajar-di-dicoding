package com.edsusantoo.bismillah.academy.ui.detail

import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.utils.DataDummy


class DetailCourseViewModel : ViewModel() {
    private var mCourse: CourseEntity? = null
    private var courseId: String? = null

    fun getCourse(): CourseEntity? {
        for (i in 0 until DataDummy.generateDummyCourses().size) {
            val courseEntity = DataDummy.generateDummyCourses()[i]
            if (courseEntity.courseId == courseId) {
                mCourse = courseEntity
            }
        }
        return mCourse
    }

    fun getModules(): List<ModuleEntity> {
        return DataDummy.generateDummyModules(getCourseId())
    }

    fun setCourseId(courseId: String) {
        this.courseId = courseId
    }

    fun getCourseId(): String {
        return courseId!!
    }
}