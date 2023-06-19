package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val noteListRepository: NoteListRepository) {
    suspend fun getNote (id: Int): Note {
        return noteListRepository.getNote(id)
    }
}