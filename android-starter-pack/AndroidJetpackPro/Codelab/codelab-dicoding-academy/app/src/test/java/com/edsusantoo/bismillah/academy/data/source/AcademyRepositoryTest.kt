package com.edsusantoo.bismillah.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edsusantoo.bismillah.academy.data.source.remote.RemoteRepository
import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class AcademyRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote: RemoteRepository = mock(RemoteRepository::class.java)
    private val academyRepository: FakeAcademyRepository = FakeAcademyRepository(remote)

    private val courseResponse: List<CourseResponse> = FakeDataDummyTest.generateRemoteDummyCourses()
    private val courseId: String = courseResponse[0].id
    private val moduleResponse: ArrayList<ModuleResponse> = FakeDataDummyTest.generateRemoteDummyModules(courseId)
    private val moduleId: String = moduleResponse[0].moduleId
    private val content: ContentResponse = FakeDataDummyTest.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourse() {
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntity = academyRepository.getAllCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntity)
        assertEquals(courseResponse.size, courseEntity.size)
    }

    @Test
    fun getAllModuleByCourse() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)
        val moduleEntity = academyRepository.getAllModulesByCourse(courseId)
        verify(remote).getModules(courseId)
        assertNotNull(moduleEntity)
        assertEquals(moduleResponse.size, moduleEntity.size)
    }

    @Test
    fun getBookmarkedCourses() {
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size, courseEntities.size)
    }

    @Test
    fun getContent() {
        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)
        `when`(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId, moduleId)
        verify(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content, resultModule?.contentEntity?.mContent)
    }


    @Test
    fun getCourseWithModules() {
        `when`(remote.getAllCourses()).thenReturn(courseResponse)
        val resultCourse = academyRepository.getCourseWithModules(courseId)
        verify(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponse[0].title, resultCourse?.title)
    }

}