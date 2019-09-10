package com.edsusantoo.bismillah.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseWithModule
import com.edsusantoo.bismillah.academy.data.source.vo.Resource
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DetailCourseViewModelTest {


    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailCourseViewModel
    private var dummyCourse: CourseEntity = FakeDataDummyTest.generateDummyCourses()[0]
    private var courseId: String = dummyCourse.courseId
    private val dummyModules = FakeDataDummyTest.generateDummyModules(courseId)

    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)


    @Mock
    lateinit var observeCourseWithModule: Observer<Resource<CourseWithModule>>


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setCourseId(courseId)



    }


    @Test
    fun getCourseWithModule() {
        val resource = Resource.success(FakeDataDummyTest.generateDummyCourseWithModules(dummyCourse, true))
        val courseEntities = MutableLiveData<Resource<CourseWithModule>>()
        courseEntities.setValue(resource)

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities)

        viewModel.courseModule.observeForever(observeCourseWithModule)

        verify(observeCourseWithModule).onChanged(resource)
    }
}