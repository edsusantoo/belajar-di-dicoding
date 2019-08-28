package com.edsusantoo.bismillah.academy.data.source.remote

import android.os.Handler
import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse
import com.edsusantoo.bismillah.academy.utils.EspressoIdlingResource
import com.edsusantoo.bismillah.academy.utils.JsonHelper


class RemoteRepository(private val jsonHelper: JsonHelper) {

    companion object {
        private var INSTANCE: RemoteRepository? = null
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        fun getInstance(helper: JsonHelper): RemoteRepository? {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(helper)
            }

            return INSTANCE
        }

    }

    fun getAllCourses(callback: LoadCoursesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getModules(courseId: String?, callback: LoadModulesCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed(
            {
                callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getContent(moduleId: String, callback: GetContentCallback) {
        EspressoIdlingResource.increment()
        val handler = Handler()
        handler.postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)

        fun onDataNotAvailable()
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)

        fun onDataNotAvailable()
    }

    interface GetContentCallback {
        fun onContentReceived(contentResponse: ContentResponse?)

        fun onDataNotAvailable()
    }

}