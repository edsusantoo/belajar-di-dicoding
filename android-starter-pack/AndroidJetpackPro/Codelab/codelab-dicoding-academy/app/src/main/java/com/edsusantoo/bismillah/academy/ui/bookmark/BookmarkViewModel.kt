package com.edsusantoo.bismillah.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {

    fun getBookmark(): List<CourseEntity> {
        return DataDummy.generateDummyCourses()
    }
}