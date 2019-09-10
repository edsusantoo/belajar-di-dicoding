package com.edsusantoo.bismillah.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.vo.Resource
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class AcademyViewModelTest {

    var USERNAME = "dicoding"

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AcademyViewModel
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)


    @Mock
    lateinit var observeGetCourse: Observer<Resource<List<CourseEntity>>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = AcademyViewModel(academyRepository)
    }


    @Test
    fun getCourse() {

        val resource: Resource<List<CourseEntity>> = Resource.success(FakeDataDummyTest.generateDummyCourses())

        val dummyResource: MutableLiveData<Resource<List<CourseEntity>>> = MutableLiveData()
        dummyResource.value = resource

        `when`(academyRepository.getAllCourses()).thenReturn(dummyResource)

        viewModel.setUsername(USERNAME)
        viewModel.course.observeForever(observeGetCourse)

        verify(observeGetCourse).onChanged(resource)
    }
}