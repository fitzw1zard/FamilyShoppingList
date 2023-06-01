package com.example.familyshoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.familyshoppinglist.domain.entity.Note

interface NoteListRepository {

    suspend fun addNote (note: Note)

    suspend fun editNote (note: Note)

    suspend fun deleteNote (note: Note)

    suspend fun getNoteItem (id: Int): Note

    fun getNoteList (): LiveData<List<Note>>


}