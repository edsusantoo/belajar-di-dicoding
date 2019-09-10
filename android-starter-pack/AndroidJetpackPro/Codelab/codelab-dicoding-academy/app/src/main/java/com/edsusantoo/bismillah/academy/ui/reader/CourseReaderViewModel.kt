package com.edsusantoo.bismillah.academy.ui.reader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity

class CourseReaderViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {
    private val courseId = MutableLiveData<String>()
    private val moduleId = MutableLiveData<String>()

    val modules = Transformations.switchMap(courseId) {
        academyRepository?.getAllModulesByCourse(it)
    }

    fun setCourseId(courseId: String) {
        this.courseId.value = courseId
    }

    val selectedModule = Transformations.switchMap(moduleId) {
        academyRepository?.getContent(it)
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId.value = moduleId
    }

    fun readContent(module: ModuleEntity) {
        academyRepository?.setReadModule(module)
    }

    fun getModuleSize(): Int {
        if (modules.value != null) {
            val moduleEntity = modules.value?.data
            if (moduleEntity != null) {
                return moduleEntity.size
            }
        }
        return 0
    }

    fun setNextPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data

            if (moduleEntities != null && moduleEntity != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    setSelectedModule(moduleEntities[position + 1].moduleId)
                }
            }
        }
    }

    fun setPrevPage() {
        if (selectedModule.value != null && modules.value != null) {
            val moduleEntity = selectedModule.value?.data
            val moduleEntities = modules.value?.data

            if (moduleEntities != null && moduleEntity != null) {
                val position = moduleEntity.position
                if (position < moduleEntities.size && position >= 0) {
                    setSelectedModule(moduleEntities[position - 1].moduleId)
                }
            }
        }
    }

}