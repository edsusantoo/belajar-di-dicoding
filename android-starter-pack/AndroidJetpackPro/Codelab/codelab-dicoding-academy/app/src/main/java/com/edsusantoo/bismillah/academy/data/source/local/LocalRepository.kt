package com.edsusantoo.bismillah.academy.data.source.local

import androidx.lifecycle.LiveData
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseWithModule
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.local.room.dao.AcademyDao


class LocalRepository(academyDao: AcademyDao) {
    private var mAcademyDao = academyDao

    companion object {
        private var INSTANCE: LocalRepository? = null

        fun getInstance(academyDao: AcademyDao): LocalRepository? {
            if (INSTANCE == null) {
                INSTANCE = LocalRepository(academyDao)
            }
            return INSTANCE
        }
    }

    fun getAllCourse(): LiveData<List<CourseEntity>> {
        return mAcademyDao.getCourse()
    }

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>> {
        return mAcademyDao.getBookmarkedCourse()
    }

    fun getCourseWithModules(courseId: String): LiveData<CourseWithModule> {
        return mAcademyDao.getCourseWithModuleId(courseId)
    }

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        return mAcademyDao.getModulesByCourseId(courseId)
    }

    fun insertCourses(courses: List<CourseEntity>) {
        mAcademyDao.insertCourses(courses)
    }

    fun insertModules(modules: List<ModuleEntity>) {
        mAcademyDao.insertModules(modules)
    }

    fun setCourseBookmark(course: CourseEntity?, newState: Boolean) {
        course?.bookmarked = newState
        mAcademyDao.updateCourse(course)
    }

    fun getModuleWithContent(moduleId: String): LiveData<ModuleEntity> {
        return mAcademyDao.getModuleById(moduleId)
    }

    fun updateContent(content: String, moduleId: String) {
        mAcademyDao.updateModuleByContent(content, moduleId)
    }

    fun setReadModule(module: ModuleEntity) {
        module.mRead = true
        mAcademyDao.updateModule(module)
    }
}