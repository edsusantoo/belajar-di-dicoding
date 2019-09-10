package com.edsusantoo.bismillah.academy.data.source

import androidx.lifecycle.LiveData
import com.edsusantoo.bismillah.academy.data.source.local.LocalRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseWithModule
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.remote.ApiResponse
import com.edsusantoo.bismillah.academy.data.source.remote.RemoteRepository
import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse
import com.edsusantoo.bismillah.academy.data.source.vo.Resource
import com.edsusantoo.bismillah.academy.utils.AppExecutors

class AcademyRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val appExecutors: AppExecutors
) : AcademyDataSource {

    companion object {
        private var INSTANCE: AcademyRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteRepository: RemoteRepository,
            appExecutors: AppExecutors
        ): AcademyRepository? {
            if (INSTANCE == null) {
                synchronized(AcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AcademyRepository(localRepository, remoteRepository, appExecutors)
                    }
                }
            }
            return INSTANCE
        }

    }

    override fun getAllCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CourseEntity>> {
                return localRepository.getAllCourse()
            }

            override fun shouldFetch(data: List<CourseEntity>): Boolean {
                return data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return remoteRepository.getAllCoursesAsLiveData()
            }

            override fun saveCallResult(data: List<CourseResponse>) {
                val courseEntities = ArrayList<CourseEntity>()

                for (courseResponse in data) {

                    courseEntities.add(
                        CourseEntity(
                            courseResponse.title,
                            courseResponse.description,
                            courseResponse.date,
                            null, courseResponse.imagePath
                        )
                    )
                }

                localRepository.insertCourses(courseEntities)
            }

        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<Resource<List<CourseEntity>>> {
        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<CourseEntity>> {
                return localRepository.getBookmarkedCourses()
            }

            override fun shouldFetch(data: List<CourseEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> {
                return null!!
            }

            override fun saveCallResult(data: List<CourseResponse>) {

            }
        }.asLiveData()
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun shouldFetch(data: CourseWithModule): Boolean? {
                return data.mModules?.isEmpty()
            }

            override fun loadFromDB(): LiveData<CourseWithModule> {
                return localRepository.getCourseWithModules(courseId)
            }


            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteRepository.getAllModulesByCourseAsLiveData(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {

                val moduleEntities = ArrayList<ModuleEntity>()

                for ((moduleId, _, title, position) in data) {
                    moduleEntities.add(ModuleEntity(moduleId, courseId, title, position, null))
                }

                localRepository.insertModules(moduleEntities)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> {
                return localRepository.getAllModulesByCourse(courseId)
            }

            override fun shouldFetch(data: List<ModuleEntity>): Boolean {
                return data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> {
                return remoteRepository.getAllModulesByCourseAsLiveData(courseId)
            }

            override fun saveCallResult(data: List<ModuleResponse>) {

                val moduleEntities = ArrayList<ModuleEntity>()

                for ((moduleId, _, title, position) in data) {
                    moduleEntities.add(ModuleEntity(moduleId, courseId, title, position, null))
                }

                localRepository.insertModules(moduleEntities)

            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> {
                return localRepository.getModuleWithContent(moduleId)
            }

            override fun shouldFetch(data: ModuleEntity): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> {
                return remoteRepository.getContentAsLiveData(moduleId)
            }

            override fun saveCallResult(data: ContentResponse) {
                localRepository.updateContent(data.content, moduleId)
            }
        }.asLiveData()
    }

    override fun setCourseBookmark(course: CourseEntity?, state: Boolean) {
        val runnable = { localRepository.setCourseBookmark(course, state) }

        appExecutors.diskIO().execute(runnable)
    }

    override fun setReadModule(module: ModuleEntity) {
        val runnable = { localRepository.setReadModule(module) }
        appExecutors.diskIO().execute(runnable)
    }
}