package com.example.familyshoppinglist.domain

interface NoteListRepository {

    fun addNote (noteItem: NoteItem)

    fun editNote (noteItem: NoteItem)

    fun deleteNote (NoteItem: NoteItem)

    fun getNoteItem (id: Int): NoteItem

    fun getNoteList (): List<NoteItem>

    fun doneNote (noteItem: NoteItem)


}