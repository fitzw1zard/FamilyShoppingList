package com.example.familyshoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.familyshoppinglist.domain.entity.Note

interface NoteListRepository {

    fun addNote (note: Note)

    fun editNote (note: Note)

    fun deleteNote (note: Note)

    fun getNoteItem (id: Int): Note

    fun getNoteList (): LiveData<List<Note>>


}