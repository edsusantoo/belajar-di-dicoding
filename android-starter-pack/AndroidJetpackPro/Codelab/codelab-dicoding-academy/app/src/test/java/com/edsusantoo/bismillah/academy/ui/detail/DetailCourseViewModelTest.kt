package com.edsusantoo.bismillah.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
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
    lateinit var observeGetCourseEntity: Observer<CourseEntity?>

    @Mock
    lateinit var observeGetModuleEntity: Observer<List<ModuleEntity>>

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)

        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setCourseId(courseId)



    }


    @Test
    fun getCourse() {
        val courseEntities = MutableLiveData<CourseEntity>()
        courseEntities.value = dummyCourse

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(courseEntities)

        viewModel.getCourse()?.observeForever(observeGetCourseEntity)

        verify(observeGetCourseEntity).onChanged(dummyCourse)

    }


    @Test
    fun getModules() {

        val moduleEntities = MutableLiveData<List<ModuleEntity>>()
        moduleEntities.value = dummyModules

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)

        viewModel.getModules()?.observeForever(observeGetModuleEntity)

        verify(observeGetModuleEntity).onChanged(dummyModules)

    }
}