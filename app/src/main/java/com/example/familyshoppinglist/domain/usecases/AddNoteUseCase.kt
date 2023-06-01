package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class AddNoteUseCase(private val noteListRepository: NoteListRepository) {
       suspend fun addNote (note: Note) {
            noteListRepository.addNote (note)
        }
}
