package com.example.familyshoppinglist.domain

class DeleteNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun deleteNote (NoteItem: NoteItem) {
        noteListRepository.deleteNote(NoteItem)
    }
}