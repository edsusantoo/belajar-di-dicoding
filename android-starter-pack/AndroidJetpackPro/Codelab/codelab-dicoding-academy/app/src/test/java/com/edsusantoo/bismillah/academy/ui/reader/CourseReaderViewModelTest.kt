package com.edsusantoo.bismillah.academy.ui.reader

import com.edsusantoo.bismillah.academy.data.ContentEntity
import com.edsusantoo.bismillah.academy.data.ModuleEntity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CourseReaderViewModelTest {
    private lateinit var viewModel: CourseReaderViewModel
    private lateinit var dummyContentEntity: ContentEntity
    private lateinit var moduleId: String

    @Before
    fun setup() {
        viewModel = CourseReaderViewModel()
        viewModel.setCourseId("a14")

        moduleId = "a14m1"

        val title: String = viewModel.getModules()[0].mTitle
        dummyContentEntity = ContentEntity(
                "<h3 class=\\\"fr-text-bordered\\\">$title</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>"
        )
    }

    @Test
    fun getModules() {
        val moduleEntities: ArrayList<ModuleEntity> = viewModel.getModules()

        assertNotNull(moduleEntities)
        assertEquals(7, moduleEntities.size)
    }

    @Test
    fun getSelectedModule() {
        viewModel.setSelectedModule(moduleId)
        val moduleEntity: ModuleEntity? = viewModel.getSelectedModule()
        assertNotNull(moduleEntity)

        val contentEntity: ContentEntity? = moduleEntity?.contentEntity
        assertNotNull(contentEntity)

        val content: String? = contentEntity?.mContent
        assertNotNull(content)

        assertEquals(content, dummyContentEntity.mContent)

    }
}