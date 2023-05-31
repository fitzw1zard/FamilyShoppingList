package com.example.familyshoppinglist.domain.repository

import androidx.lifecycle.LiveData
import com.example.familyshoppinglist.domain.entity.NoteItem

interface NoteListRepository {

    fun addNote (noteItem: NoteItem)

    fun editNote (noteItem: NoteItem)

    fun deleteNote (NoteItem: NoteItem)

    fun getNoteItem (id: Int): NoteItem

    fun getNoteList (): LiveData<List<NoteItem>>


}