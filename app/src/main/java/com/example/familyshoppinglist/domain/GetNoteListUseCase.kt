package com.example.familyshoppinglist.domain

class GetNoteListUseCase(private val noteListRepository: NoteListRepository) {
    fun getNoteList (): List<NoteItem> {
        return noteListRepository.getNoteList()
    }
}