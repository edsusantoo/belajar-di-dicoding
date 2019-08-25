package com.edsusantoo.bismillah.academy.ui.reader

import com.edsusantoo.bismillah.academy.data.ContentEntity
import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class CourseReaderViewModelTest {
    private lateinit var viewModel: CourseReaderViewModel

    private var dummyCourse: CourseEntity = FakeDataDummyTest.generateDummyCourses()[0]
    private var courseId: String = dummyCourse.courseId
    private var dummyModules: List<ModuleEntity> = FakeDataDummyTest.generateDummyModules(courseId)
    private val moduleId: String = dummyModules[0].mModuleId
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.setCourseId(courseId)

    }

    @Test
    fun getModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(dummyModules)

        val moduleEntities: List<ModuleEntity>? = viewModel.getModules()

        verify(academyRepository).getAllModulesByCourse(courseId)

        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities?.size)
    }

    @Test
    fun getSelectedModule() {
        val conenttn =
            "<h3 class=\"fr-text-bordered\">Modul 0 : Introduction</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"

        val moduleEntity: ModuleEntity? = dummyModules[0]

        moduleEntity?.contentEntity = ContentEntity(conenttn)
        viewModel.setSelectedModule(moduleId)

        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(moduleEntity)

        val entity: ModuleEntity? = viewModel.getSelectedModule()
        verify(academyRepository).getContent(courseId, moduleId)
        assertNotNull(entity)

        val contentEntity = entity?.contentEntity
        assertNotNull(contentEntity)

        val resultContent = contentEntity?.mContent
        assertNotNull(resultContent)

        assertEquals(conenttn, resultContent)

    }
}