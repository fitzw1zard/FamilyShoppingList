package com.example.familyshoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import javax.inject.Inject

class GetNoteListUseCase @Inject constructor(private val noteListRepository: NoteListRepository) {
    fun getNoteList (): LiveData<List<Note>> {
        return noteListRepository.getNotes()
    }
}