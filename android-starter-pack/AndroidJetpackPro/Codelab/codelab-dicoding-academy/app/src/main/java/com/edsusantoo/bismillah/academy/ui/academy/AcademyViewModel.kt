package com.edsusantoo.bismillah.academy.ui.academy

import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.utils.DataDummy

class AcademyViewModel : ViewModel() {
    fun getCourse(): List<CourseEntity> {
        return DataDummy.generateDummyCourses()
    }
}