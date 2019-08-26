package com.edsusantoo.bismillah.academy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class AcademyViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AcademyViewModel
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)


    @Mock
    lateinit var observeGetCourse: Observer<List<CourseEntity>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = AcademyViewModel(academyRepository)
        viewModel.getCourse()?.observeForever(observeGetCourse)
    }


    @Test
    fun getCourse() {

        val dummyCourse: ArrayList<CourseEntity> = FakeDataDummyTest.generateDummyCourses()

        val courses: MutableLiveData<List<CourseEntity>> = MutableLiveData()
        courses.value = dummyCourse

        `when`(academyRepository.getAllCourses()).thenReturn(courses)

        verify(observeGetCourse).onChanged(dummyCourse)
    }
}