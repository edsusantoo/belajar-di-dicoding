package com.edsusantoo.bismillah.mynote.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.mynote.database.Note
import com.edsusantoo.bismillah.mynote.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {

    private var mNoteRepository: NoteRepository = NoteRepository(application)


    fun getAllNotes(): LiveData<List<Note>> {
        return mNoteRepository.getAllNotes()
    }

}