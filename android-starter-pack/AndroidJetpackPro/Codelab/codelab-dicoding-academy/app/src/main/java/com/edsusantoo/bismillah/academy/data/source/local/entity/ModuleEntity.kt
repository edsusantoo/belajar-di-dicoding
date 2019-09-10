package com.edsusantoo.bismillah.academy.data.source.local.entity

import androidx.room.*

@Entity(
    tableName = "moduleentities",
    primaryKeys = ["moduleId", "courseId"],
    foreignKeys = [ForeignKey(entity = CourseEntity::class, parentColumns = ["courseId"], childColumns = ["courseId"])],
    indices = [Index(value = ["moduleId"]), Index(value = ["courseId"])]
)
class ModuleEntity(
    @ColumnInfo(name = "moduleId") var moduleId: String,
    @ColumnInfo(name = "courseId") var courseId: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "position") var position: Int,
    @ColumnInfo(name = "read") var mRead: Boolean? = false
) {

    @Embedded
    var contentEntity: ContentEntity? = null
}