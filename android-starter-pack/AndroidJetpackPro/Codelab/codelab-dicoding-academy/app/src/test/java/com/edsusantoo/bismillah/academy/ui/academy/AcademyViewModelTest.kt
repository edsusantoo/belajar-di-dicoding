package com.edsusantoo.bismillah.academy.ui.academy

import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class AcademyViewModelTest {
    private lateinit var viewModel: AcademyViewModel
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourse() {
        `when`(academyRepository.getAllCourses()).thenReturn(FakeDataDummyTest.generateDummyCourses())

        val courseEntities: List<CourseEntity>? = viewModel.getCourse()

        verify(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}