package com.edsusantoo.bismillah.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {
    fun getCourse(): LiveData<List<CourseEntity>>? {
        return academyRepository?.getAllCourses()
    }

}