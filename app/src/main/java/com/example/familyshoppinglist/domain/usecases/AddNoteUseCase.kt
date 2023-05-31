package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class AddNoteUseCase(private val noteListRepository: NoteListRepository) {
        fun addNote (noteItem: NoteItem) {
            noteListRepository.addNote (noteItem)
        }
}
