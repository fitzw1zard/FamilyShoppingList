package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class EditNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun editNote (noteItem: NoteItem) {
        noteListRepository.editNote (noteItem)
    }
}
