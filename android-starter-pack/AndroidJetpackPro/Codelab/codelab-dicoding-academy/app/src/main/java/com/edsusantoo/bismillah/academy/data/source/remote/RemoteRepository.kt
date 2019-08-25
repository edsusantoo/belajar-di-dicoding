package com.edsusantoo.bismillah.academy.data.source.remote

import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse
import com.edsusantoo.bismillah.academy.utils.JsonHelper


class RemoteRepository(private val jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }

            return INSTANCE
        }

    }

    fun getAllCourses(): List<CourseResponse> {
        return jsonHelper.loadCourses()
    }

    fun getModules(courseId: String?): List<ModuleResponse> {
        return jsonHelper.loadModule(courseId)
    }

    fun getContent(moduleId: String): ContentResponse? {
        return jsonHelper.loadContent(moduleId)
    }

}