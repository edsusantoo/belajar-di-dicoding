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
import org.mockito.Mockito
import org.mockito.Mockito.*


class AcademyRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote: RemoteRepository = mock(RemoteRepository::class.java)
    private val academyRepository: FakeAcademyRepository = FakeAcademyRepository(remote)

    private val courseResponse: ArrayList<CourseResponse> = FakeDataDummyTest.generateRemoteDummyCourses()
    private val courseId: String = courseResponse[0].id
    private val moduleResponse: ArrayList<ModuleResponse> = FakeDataDummyTest.generateRemoteDummyModules(courseId)
    private val moduleId: String = moduleResponse[0].moduleId
    private val content: ContentResponse = FakeDataDummyTest.generateRemoteDummyContent(moduleId)

    @Before
    fun setup() {
        // MockitoAnnotations.initMocks(this)
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
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponse.size, result.size)
    }

    @Test
    fun getAllModuleByCourse() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadModulesCallback)
                .onAllModulesReceived(moduleResponse)

            return@doAnswer null
        }.`when`(remote).getModules(eq(courseId), any())

        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(remote, times(1)).getModules(eq(courseId), any())

        assertNotNull(result)
        assertEquals(moduleResponse.size, result.size)


    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer {
            (it.arguments[0] as RemoteRepository.LoadCoursesCallback)
                .onAllCoursesReceived(courseResponse)

            return@doAnswer null
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertEquals(courseResponse.size, result.size)

    }

    //TODO:Masih error di moduleid yang null
    @Test
    fun getContent() {
        doAnswer {
            (it.arguments[1] as RemoteRepository.LoadModulesCallback)
                .onAllModulesReceived(moduleResponse)

            return@doAnswer null
        }.`when`(remote).getModules(eq(courseId), any())

        doAnswer {
            (it.arguments[1] as RemoteRepository.GetContentCallback)
                .onContentReceived(content)
            return@doAnswer null
        }.`when`(remote).getContent(eq(moduleId), any())

        val resultContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        verify(remote, times(1))
            .getModules(eq(courseId), any())

        verify(remote, times(1))
            .getContent(eq(moduleId), any())

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
        }.`when`(remote).getAllCourses(any())

        val result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(remote, times(1)).getAllCourses(any())

        assertNotNull(result)
        assertNotNull(result?.title)
        assertEquals(courseResponse[0].title, result?.title)
    }

    /**
     * halper to null interface
     */
    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T


}