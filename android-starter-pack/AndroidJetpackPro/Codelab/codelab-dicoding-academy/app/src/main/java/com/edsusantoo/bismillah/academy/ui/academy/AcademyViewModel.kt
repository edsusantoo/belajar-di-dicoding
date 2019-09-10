package com.edsusantoo.bismillah.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {

    private val mLogin = MutableLiveData<String>()

    val course: LiveData<Resource<List<CourseEntity>>> = Transformations.switchMap(mLogin) {
        academyRepository?.getAllCourses()
    }

    fun setUsername(username: String) {
        mLogin.value = username
    }
}