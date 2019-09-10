package com.edsusantoo.bismillah.academy.ui.bookmark

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
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class BookmarkViewModelTest {


    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BookmarkViewModel
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)


    @Mock
    lateinit var observeGetCourse: Observer<Resource<List<CourseEntity>>>


    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        viewModel = BookmarkViewModel(academyRepository)

    }

    @Test
    fun getBookmark() {

        val resource: Resource<List<CourseEntity>> = Resource.success(FakeDataDummyTest.generateDummyCourses())

        val dummyCourses = MutableLiveData<Resource<List<CourseEntity>>>()
        dummyCourses.value = resource

        `when`(academyRepository.getBookmarkedCourses()).thenReturn(dummyCourses)

        viewModel.getBookmark()?.observeForever(observeGetCourse)

        verify(observeGetCourse).onChanged(resource)


    }
}