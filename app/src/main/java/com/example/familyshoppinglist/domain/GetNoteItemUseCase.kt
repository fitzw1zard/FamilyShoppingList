package com.example.familyshoppinglist.domain

class GetNoteItemUseCase(private val noteListRepository: NoteListRepository) {
    fun getNoteItem (id: Int): NoteItem {
        return noteListRepository.getNoteItem(id)
    }
}