package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class GetNoteUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun getNote (id: Int): Note {
        return noteListRepository.getNote(id)
    }
}