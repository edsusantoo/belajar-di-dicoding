package com.edsusantoo.bismillah.academy.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courseentities")
class CourseEntity(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "deadline") var deadline: String,
    @ColumnInfo(name = "bookmarked") var bookmarked: Boolean? = false,
    @ColumnInfo(name = "imagePath") var imagePath: String

) {
    @PrimaryKey
    @ColumnInfo(name = "courseId")
    var courseId = String()
}