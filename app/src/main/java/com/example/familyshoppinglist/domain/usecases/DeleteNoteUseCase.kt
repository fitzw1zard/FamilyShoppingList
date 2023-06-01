package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class DeleteNoteUseCase(private val noteListRepository: NoteListRepository) {
    suspend fun deleteNote (Note: Note) {
        noteListRepository.deleteNote(Note)
    }
}