package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import javax.inject.Inject

class EditNoteUseCase @Inject constructor(private val noteListRepository: NoteListRepository) {
    suspend fun editNote (note: Note) {
        noteListRepository.editNote (note)
    }
}
