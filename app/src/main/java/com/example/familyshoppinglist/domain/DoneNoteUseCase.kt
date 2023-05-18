package com.example.familyshoppinglist.domain

class DoneNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun doneNote (noteItem: NoteItem) {
        noteListRepository.doneNote(noteItem)
    }
}