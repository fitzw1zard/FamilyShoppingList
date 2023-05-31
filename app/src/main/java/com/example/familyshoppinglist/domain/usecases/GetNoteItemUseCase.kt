package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class GetNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    fun getNoteItem (id: Int): Note {
        return noteListRepository.getNoteItem(id)
    }
}