package com.example.familyshoppinglist.domain

class EditNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun editNote (noteItem: NoteItem) {
        noteListRepository.editNote(noteItem)
    }
}