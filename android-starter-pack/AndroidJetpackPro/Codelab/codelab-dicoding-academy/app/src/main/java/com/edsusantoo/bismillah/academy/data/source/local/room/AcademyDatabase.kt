package com.edsusantoo.bismillah.academy.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edsusantoo.bismillah.academy.data.source.local.entity.CourseEntity
import com.edsusantoo.bismillah.academy.data.source.local.entity.ModuleEntity
import com.edsusantoo.bismillah.academy.data.source.local.room.dao.AcademyDao

@Database(
    entities = [CourseEntity::class, ModuleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AcademyDatabase : RoomDatabase() {

    abstract fun academyDao(): AcademyDao

    companion object {
        @Volatile
        private var INSTANCE: AcademyDatabase? = null

        fun getInstace(context: Context): AcademyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(AcademyDatabase::class.java) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AcademyDatabase::class.java,
                    "Academies.db"
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}