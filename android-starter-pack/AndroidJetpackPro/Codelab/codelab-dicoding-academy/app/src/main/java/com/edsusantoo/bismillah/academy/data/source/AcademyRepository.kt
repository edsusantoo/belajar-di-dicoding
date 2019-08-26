package com.edsusantoo.bismillah.academy.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edsusantoo.bismillah.academy.data.ContentEntity
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.remote.RemoteRepository
import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse


class AcademyRepository(private val remoteRepository: RemoteRepository) : AcademyDataSource {

    companion object {
        private var INSTANCE: AcademyRepository? = null

        fun getInstance(remoteRepository: RemoteRepository): AcademyRepository? {
            if (INSTANCE == null) {
                synchronized(AcademyRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = AcademyRepository(remoteRepository)
                    }
                }
            }
            return INSTANCE
        }

    }


    override fun getAllCourses(): LiveData<List<CourseEntity>> {

        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (i in 0 until courseResponses.size) {
                    val (id, title, description, date, imagePath) = courseResponses[i]
                    val course = CourseEntity(
                        id,
                        title,
                        description,
                        date,
                        false,
                        imagePath
                    )

                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {

            }

        })

        return courseResults
    }

    override fun getCourseWithModules(courseId: String?): LiveData<CourseEntity?> {
        val courseResult = MutableLiveData<CourseEntity>()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                for (i in courseResponses.indices) {
                    val (id, title, description, date, imagePath) = courseResponses[i]
                    if (id == courseId) {
                        val course = CourseEntity(
                            id,
                            title,
                            description,
                            date,
                            false,
                            imagePath
                        )
                        courseResult.postValue(course)
                    }
                }
            }

            override fun onDataNotAvailable() {

            }
        })

        return courseResult
    }

    override fun getAllModulesByCourse(courseId: String?): LiveData<List<ModuleEntity>> {
        val moduleResults = MutableLiveData<List<ModuleEntity>>()

        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (i in moduleResponses.indices) {
                    val (moduleId, courseId1, title, position) = moduleResponses[i]
                    val course = ModuleEntity(
                        moduleId,
                        courseId1,
                        title,
                        position,
                        false
                    )

                    moduleList.add(course)
                }
                moduleResults.postValue(moduleList)
            }

            override fun onDataNotAvailable() {

            }
        })


        return moduleResults
    }

    override fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()

        remoteRepository.getAllCourses(object : RemoteRepository.LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (i in courseResponses.indices) {
                    val (id, title, description, date, imagePath) = courseResponses[i]
                    val course = CourseEntity(
                        id,
                        title,
                        description,
                        date,
                        false,
                        imagePath
                    )
                    courseList.add(course)
                }
                courseResults.postValue(courseList)
            }

            override fun onDataNotAvailable() {

            }
        })

        return courseResults
    }

    override fun getContent(courseId: String?, moduleId: String?): LiveData<ModuleEntity?> {
        val moduleResult = MutableLiveData<ModuleEntity>()
        remoteRepository.getModules(courseId, object : RemoteRepository.LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val module: ModuleEntity
                for (i in moduleResponses.indices) {
                    val (id, courseId1, title, position) = moduleResponses[i]

                    if (id == moduleId) {
                        module = ModuleEntity(id, courseId1, title, position, false)

                        remoteRepository.getContent(moduleId, object : RemoteRepository.GetContentCallback {
                            override fun onContentReceived(contentResponse: ContentResponse?) {
                                module.contentEntity = ContentEntity(contentResponse!!.content)
                                moduleResult.postValue(module)
                            }

                            override fun onDataNotAvailable() {

                            }
                        })
                        break
                    }
                }
            }

            override fun onDataNotAvailable() {

            }
        })

        return moduleResult
    }

}