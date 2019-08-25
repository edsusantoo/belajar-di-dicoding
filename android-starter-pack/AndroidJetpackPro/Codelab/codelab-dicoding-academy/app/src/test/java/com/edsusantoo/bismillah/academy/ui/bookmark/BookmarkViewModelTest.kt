package com.edsusantoo.bismillah.academy.ui.bookmark

import com.edsusantoo.bismillah.academy.data.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.utils.FakeDataDummyTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel
    private var academyRepository: AcademyRepository = mock(AcademyRepository::class.java)

    @Before
    fun setup() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmark() {
        `when`(academyRepository.getBookmarkedCourses()).thenReturn(FakeDataDummyTest.generateDummyCourses())

        val courseEntities: List<CourseEntity>? = viewModel.getBookmark()

        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
    }
}