package com.edsusantoo.bismillah.academy.ui.reader

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.local.entity.ContentEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.vo.Resource
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class CourseReaderViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CourseReaderViewModel

    private var dummyCourse: CourseEntity = FakeDataDummyTest.generateDummyCourses()[0]
    private var courseId: String = dummyCourse.courseId
    private var dummyModules: List<ModuleEntity> = FakeDataDummyTest.generateDummyModules(courseId)
    private val moduleId: String = dummyModules[0].moduleId
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Mock
    lateinit var observeGetModuleEntityList: Observer<Resource<List<ModuleEntity>>>


    @Mock
    lateinit var observeGetModuleEntity: Observer<Resource<ModuleEntity>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.setCourseId(courseId)

    }


    @Test
    fun getModules() {

        val moduleEntities = MutableLiveData<Resource<List<ModuleEntity>>>()
        val resource = Resource.success(dummyModules)
        moduleEntities.setValue(resource)

        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(moduleEntities)

        viewModel.modules.observeForever(observeGetModuleEntityList)

        verify(observeGetModuleEntityList).onChanged(resource)
    }


    @Test
    fun getSelectedModule() {

        val moduleEntity = MutableLiveData<Resource<ModuleEntity>>()

        val dummyModule = dummyModules[0]
        val content =
            "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        dummyModule.contentEntity = ContentEntity(content)
        val resource = Resource.success(dummyModule)
        moduleEntity.setValue(resource)

        `when`(academyRepository.getContent(moduleId)).thenReturn(moduleEntity)

        viewModel.setSelectedModule(moduleId)

        viewModel.selectedModule.observeForever(observeGetModuleEntity)
        verify(observeGetModuleEntity).onChanged(resource)
    }
}