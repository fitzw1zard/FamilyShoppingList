package com.example.familyshoppinglist.data

import com.example.familyshoppinglist.domain.NoteItem
import com.example.familyshoppinglist.domain.NoteListRepository

object NoteListRepositoryImpl: NoteListRepository {

   private val noteList = mutableListOf<NoteItem>()

    private var autoIncrementId = 0

        override fun addNote (noteItem: NoteItem) {
            if (noteItem.id == NoteItem.UNDEFINED_ID) {
                noteItem.id = autoIncrementId++
            }
            noteList.add(noteItem)
        }

        override fun editNote (noteItem: NoteItem) {
            val oldElement = getNoteItem(noteItem.id)
            noteList.remove(oldElement)
            noteList.add(noteItem)

        }

        override fun deleteNote (noteItem: NoteItem) {
            noteList.remove(noteItem)
        }

        override fun getNoteItem (id: Int): NoteItem {
            return noteList.first { it.id == id }
        }

        override fun getNoteList (): List<NoteItem> {
            return noteList.toList()
        }

        override fun doneNote (noteItem: NoteItem) {
           noteItem.isDone = true
        }
}