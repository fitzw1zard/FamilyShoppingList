package com.example.familyshoppinglist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

    val notes = getNoteListUseCase.getNoteList()


    fun changeNoteState(note: Note) {
        viewModelScope.launch {
            val changedNote = note.copy(isDone = note.isDone.not())
            editNoteUseCase.editNote(changedNote)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(note)
        }
    }

}