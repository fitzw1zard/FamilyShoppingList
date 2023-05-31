package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class DeleteNoteUseCase(private val noteListRepository: NoteListRepository) {
    fun deleteNote (NoteItem: NoteItem) {
        noteListRepository.deleteNote(NoteItem)
    }
}