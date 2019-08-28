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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
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
    }


    @Test
    fun getCourse() {

        val dummyCourse: ArrayList<CourseEntity> = FakeDataDummyTest.generateDummyCourses()

        val courses: MutableLiveData<List<CourseEntity>> = MutableLiveData()
        courses.value = dummyCourse

        `when`(academyRepository.getAllCourses()).thenReturn(courses)

        viewModel.getCourse()?.observeForever(observeGetCourse)

        verify(observeGetCourse).onChanged(dummyCourse)
    }
}