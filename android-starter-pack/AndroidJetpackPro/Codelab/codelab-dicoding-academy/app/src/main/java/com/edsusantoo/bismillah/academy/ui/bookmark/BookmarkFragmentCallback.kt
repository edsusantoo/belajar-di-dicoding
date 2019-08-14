package com.edsusantoo.bismillah.academy.ui.bookmark

import com.edsusantoo.bismillah.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(courseEntity: CourseEntity)
}