package com.edsusantoo.bismillah.academy.ui.bookmark

import com.edsusantoo.bismillah.academy.data.CourseEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest {
    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setup() {
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmark() {
        val courseEntities: List<CourseEntity> = viewModel.getBookmark()

        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}