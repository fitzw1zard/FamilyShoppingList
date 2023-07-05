package com.example.familyshoppinglist.domain.usecases

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(private val noteListRepository: NoteListRepository) {
   fun getNotes (): Flow<List<Note>> {
        return noteListRepository.getNotes()
    }
}