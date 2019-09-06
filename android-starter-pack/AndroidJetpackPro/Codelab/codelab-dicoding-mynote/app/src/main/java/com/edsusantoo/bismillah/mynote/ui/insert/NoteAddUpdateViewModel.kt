package com.edsusantoo.bismillah.mynote.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.edsusantoo.bismillah.mynote.database.Note
import com.edsusantoo.bismillah.mynote.repository.NoteRepository

class NoteAddUpdateViewModel(application: Application) : ViewModel() {

    private var mNoteRepository: NoteRepository = NoteRepository(application)


    fun insert(note: Note?) {
        mNoteRepository.insert(note)
    }

    fun delete(note: Note?) {
        mNoteRepository.delete(note)
    }

    fun update(note: Note?) {
        mNoteRepository.update(note)
    }


}