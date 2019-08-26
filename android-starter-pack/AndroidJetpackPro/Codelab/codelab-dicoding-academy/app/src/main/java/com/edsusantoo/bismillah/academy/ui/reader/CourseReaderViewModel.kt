package com.edsusantoo.bismillah.academy.ui.reader

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository

class CourseReaderViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {
    private var courseId: String? = null
    private var moduleId: String? = null

    fun getModules(): LiveData<List<ModuleEntity>>? {
        return academyRepository?.getAllModulesByCourse(courseId)
    }

    fun setCourseId(mCourseId: String) {
        this.courseId = mCourseId
    }

    fun getSelectedModule(): LiveData<ModuleEntity?>? {
        return academyRepository?.getContent(courseId, moduleId)
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }
}