package com.edsusantoo.bismillah.academy.ui.academy

import com.edsusantoo.bismillah.academy.data.CourseEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AcademyViewModelTest {
    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setup() {
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourse() {
        val courseEntities: List<CourseEntity> = viewModel.getCourse()

        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}