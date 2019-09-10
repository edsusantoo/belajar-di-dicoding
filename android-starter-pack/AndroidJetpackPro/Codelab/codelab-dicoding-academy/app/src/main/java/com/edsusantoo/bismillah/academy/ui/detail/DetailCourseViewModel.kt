package com.edsusantoo.bismillah.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseWithModule
import com.edsusantoo.bismillah.academy.data.source.vo.Resource


class DetailCourseViewModel(private val academyRepository: AcademyRepository?) : ViewModel() {
    private val mCourseEntity: CourseEntity? = null

    private val courseId = MutableLiveData<String>()

    val courseModule: LiveData<Resource<CourseWithModule>> = Transformations.switchMap(courseId) {
        academyRepository?.getCourseWithModules(it)
    }

    fun setCourseId(mCourseId: String) {
        this.courseId.value = mCourseId
    }

    fun getCourseId(): String? {
        if (courseId.value == null) return null

        return courseId.value
    }

    fun setBookmark() {
        if (courseModule.value != null) {
            val courseWithModule = courseModule.value?.data

            if (courseWithModule != null) {
                val courseEntity = courseWithModule.mCourse

                // Kode di bawah menggunakan tanda seru (!),
                // karena akan mengganti status dari apakah sudah di bookmark atau tidak menjadi apakah sudah siap dibookmark atau tidak

                val newState = courseEntity?.bookmarked?.not()
                academyRepository?.setCourseBookmark(courseEntity, newState!!)

            }
        }
    }
}