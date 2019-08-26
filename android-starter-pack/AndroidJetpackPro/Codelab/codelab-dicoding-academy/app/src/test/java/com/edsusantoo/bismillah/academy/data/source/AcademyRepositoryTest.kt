package com.edsusantoo.bismillah.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edsusantoo.bismillah.academy.data.source.remote.RemoteRepository
import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import com.edsusantoo.bismillah.academy.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
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

    @Before
    fun setup() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun getAllCourse() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)

            return@doAnswer null
        }.`when`(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())

        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))

        assertNotNull(result)
        assertEquals(courseResponse.size, result.size)
    }

    @Test
    fun getAllModuleByCourse() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadModulesCallback)
                .onAllModulesReceived(moduleResponse)

            return@doAnswer null
        }.`when`(remote).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))

        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(remote, times(1)).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))

        assertNotNull(result)
        assertEquals(moduleResponse.size, result.size)


    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)

            return@doAnswer null
        }.`when`(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))

        assertNotNull(result)
        assertEquals(courseResponse.size, result.size)

    }

    @Test
    fun getContent() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadModulesCallback)
                .onAllModulesReceived(moduleResponse)

            return@doAnswer null
        }.`when`(remote).getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))

        doAnswer {
            (it.arguments[0] as RemoteRepository.GetContentCallback)
                .onContentReceived(content)
            return@doAnswer null
        }.`when`(remote).getContent(eq(courseId), any(RemoteRepository.GetContentCallback::class.java))

        val resultContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        verify(remote, times(1))
            .getModules(eq(courseId), any(RemoteRepository.LoadModulesCallback::class.java))

        verify(remote, times(1))
            .getContent(eq(moduleId), any(RemoteRepository.GetContentCallback::class.java))

        assertNotNull(resultContent)
        assertNotNull(resultContent?.contentEntity)
        assertNotNull(resultContent?.contentEntity?.mContent)
        assertEquals(content.content, resultContent?.contentEntity?.mContent)

    }


    @Test
    fun getCourseWithModules() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)

            return@doAnswer null
        }.`when`(remote).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))

        val result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(remote, times(1)).getAllCourses(any(RemoteRepository.LoadCoursesCallback::class.java))

        assertNotNull(result)
        assertNotNull(result?.title)
        assertEquals(courseResponse[0].title, result?.title)
    }

}