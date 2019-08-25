package com.edsusantoo.bismillah.academy.ui.detail

import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class DetailCourseViewModelTest {
    private lateinit var viewModel: DetailCourseViewModel
    private var dummyCourse: CourseEntity = FakeDataDummyTest.generateDummyCourses()[0]
    private var courseId: String = dummyCourse.courseId

    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setCourseId(courseId)

    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(dummyCourse)

        val courseEntity: CourseEntity? = viewModel.getCourse()
        verify(academyRepository).getCourseWithModules(courseId)

        assertNotNull(courseEntity)
        assertEquals(dummyCourse.courseId, courseEntity?.courseId)
        assertEquals(dummyCourse.deadline, courseEntity?.deadline)
        assertEquals(dummyCourse.description, courseEntity?.description)
        assertEquals(dummyCourse.imagePath, courseEntity?.imagePath)
        assertEquals(dummyCourse.title, courseEntity?.title)
    }

    @Test
    fun getModules() {
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(
            FakeDataDummyTest.generateDummyModules(
                courseId
            )
        )

        val moduleEntity: List<ModuleEntity>? = viewModel.getModules()

        verify(academyRepository).getAllModulesByCourse(courseId)

        assertNotNull(moduleEntity)
        assertEquals(7, moduleEntity?.size)
    }
}