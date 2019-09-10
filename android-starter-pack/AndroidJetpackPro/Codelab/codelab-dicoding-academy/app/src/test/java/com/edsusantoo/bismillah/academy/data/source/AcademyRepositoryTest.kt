package com.edsusantoo.bismillah.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.edsusantoo.bismillah.academy.data.source.local.LocalRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseWithModule
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.remote.RemoteRepository
import com.edsusantoo.bismillah.academy.data.source.remote.response.ContentResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.CourseResponse
import com.edsusantoo.bismillah.academy.data.source.remote.response.ModuleResponse
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import com.edsusantoo.bismillah.academy.utils.InstantAppExecutors
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

    private val localRepository: LocalRepository = mock(LocalRepository::class.java)
    private val instantAppExecutors = mock(InstantAppExecutors::class.java)
    private val remote: RemoteRepository = mock(RemoteRepository::class.java)

    private val academyRepository: FakeAcademyRepository =
        FakeAcademyRepository(localRepository, remote, instantAppExecutors)


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
        val dummyCourses = MutableLiveData<List<CourseEntity>>()
        dummyCourses.setValue(FakeDataDummyTest.generateDummyCourses())

        `when`(localRepository.getAllCourse()).thenReturn(dummyCourses)

        val result = LiveDataTestUtil.getValue(academyRepository.getAllCourses())

        verify(localRepository).getAllCourse()
        assertNotNull(result.data)
        assertEquals(courseResponse.size, result.data?.size)
    }

    @Test
    fun getAllModuleByCourse() {
        val dummyModules = MutableLiveData<List<ModuleEntity>>()
        dummyModules.value = FakeDataDummyTest.generateDummyModules(courseId)

        `when`(localRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)

        val result = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(localRepository).getAllModulesByCourse(courseId)
        assertNotNull(result.data)
        assertEquals(moduleResponse.size, result.data?.size)
    }

    @Test
    fun getBookmarkedCourses() {
        val dummyCourses = MutableLiveData<List<CourseEntity>>()
        dummyCourses.value = FakeDataDummyTest.generateDummyCourses()

        `when`(localRepository.getBookmarkedCourses()).thenReturn(dummyCourses)

        val result = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(localRepository).getBookmarkedCourses()
        assertNotNull(result.data)
        assertEquals(courseResponse.size, result.data?.size)

    }

    //TODO:Masih error di moduleid yang null
    @Test
    fun getContent() {
        val dummyEntity = MutableLiveData<ModuleEntity>()
        dummyEntity.setValue(FakeDataDummyTest.generateDummyModuleWithContent(moduleId))

        `when`(localRepository.getModuleWithContent(courseId)).thenReturn(dummyEntity)

        val result = LiveDataTestUtil.getValue(academyRepository.getContent(courseId))

        verify(localRepository).getModuleWithContent(courseId)
        assertNotNull(result)

        assertNotNull(result.data)
        assertNotNull(result.data?.contentEntity)
        assertNotNull(result.data?.contentEntity?.mContent)
        assertEquals(content.content, result.data?.contentEntity?.mContent)
    }


    @Test
    fun getCourseWithModules() {
        val dummyEntity = MutableLiveData<CourseWithModule>()
        dummyEntity.setValue(
            FakeDataDummyTest.generateDummyCourseWithModules(
                FakeDataDummyTest.generateDummyCourses()[0],
                false
            )
        )

        `when`(localRepository.getCourseWithModules(courseId)).thenReturn(dummyEntity)

        val result = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(localRepository).getCourseWithModules(courseId)
        assertNotNull(result.data)
        assertNotNull(result.data?.mCourse?.title)
        assertEquals(courseResponse[0].title, result.data?.mCourse?.title)
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