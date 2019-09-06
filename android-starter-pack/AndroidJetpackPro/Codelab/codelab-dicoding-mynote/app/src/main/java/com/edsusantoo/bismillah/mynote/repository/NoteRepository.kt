package com.edsusantoo.bismillah.mynote.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.edsusantoo.bismillah.mynote.database.Note
import com.edsusantoo.bismillah.mynote.database.NoteDao
import com.edsusantoo.bismillah.mynote.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {

    private var mNotesDao: NoteDao
    private var executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return mNotesDao.getAllNotes()
    }

    fun insert(note: Note?) {
        executorService.execute {
            mNotesDao.insert(note)
        }
    }

    fun delete(note: Note?) {
        executorService.execute {
            mNotesDao.delete(note)
        }
    }

    fun update(note: Note?) {
        executorService.execute {
            mNotesDao.update(note)
        }
    }


}