package com.edsusantoo.bismillah.mynotes;

import android.database.Cursor;

public interface LoadNotesCallback {
    void preExecute();

    void postExecute(Cursor notes);
}
