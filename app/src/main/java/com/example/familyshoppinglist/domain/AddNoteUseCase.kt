package com.example.familyshoppinglist.domain

class AddNoteUseCase(private val noteListRepository: NoteListRepository) {
        fun addNote (noteItem: NoteItem) {
            noteListRepository.addNote (noteItem)
        }
}
