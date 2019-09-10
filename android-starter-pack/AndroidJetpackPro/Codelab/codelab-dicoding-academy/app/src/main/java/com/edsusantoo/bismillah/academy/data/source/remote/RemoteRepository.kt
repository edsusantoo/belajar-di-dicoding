package com.edsusantoo.bismillah.academy.data.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getAllCoursesAsLiveData(): LiveData<ApiResponse<List<CourseResponse>>> {
        EspressoIdlingResource.increment()
        val resultCourse = MutableLiveData<ApiResponse<List<CourseResponse>>>()

        val handler = Handler()
        handler.postDelayed({
            resultCourse.value = ApiResponse.success(jsonHelper.loadCourses())
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultCourse
    }

    fun getAllModulesByCourseAsLiveData(courseId: String): LiveData<ApiResponse<List<ModuleResponse>>> {

        EspressoIdlingResource.increment()

        val resultModules = MutableLiveData<ApiResponse<List<ModuleResponse>>>()

        val handler = Handler()
        handler.postDelayed({
            resultModules.value = ApiResponse.success(jsonHelper.loadModule(courseId))
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultModules
    }

    fun getContentAsLiveData(moduleId: String): LiveData<ApiResponse<ContentResponse>> {
        EspressoIdlingResource.increment()
        val resultContent = MutableLiveData<ApiResponse<ContentResponse>>()

        val handler = Handler()
        handler.postDelayed({
            resultContent.value = ApiResponse.success(jsonHelper.loadContent(moduleId))
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                EspressoIdlingResource.decrement()
            }
        }, SERVICE_LATENCY_IN_MILLIS)

        return resultContent
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