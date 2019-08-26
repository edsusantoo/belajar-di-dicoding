package com.edsusantoo.bismillah.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {

    fun getBookmark(): LiveData<List<CourseEntity>>? {
        return academyRepository?.getBookmarkedCourses()
    }
}