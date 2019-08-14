package com.edsusantoo.bismillah.academy.data

data class ModuleEntity(
        val mModuleId: String,
        val mCourseId: String,
        val mTitle: String,
        val mPosition: Int,
        val mRead: Boolean?
) {
    lateinit var contentEntity: ContentEntity
}