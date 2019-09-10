package com.edsusantoo.bismillah.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.vo.Resource

class BookmarkViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {

    fun getBookmark(): LiveData<Resource<List<CourseEntity>>>? {
        return academyRepository?.getBookmarkedCourses()
    }
}