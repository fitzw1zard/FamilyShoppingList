package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class GetNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    fun getNoteItem (id: Int): NoteItem {
        return noteListRepository.getNoteItem(id)
    }
}