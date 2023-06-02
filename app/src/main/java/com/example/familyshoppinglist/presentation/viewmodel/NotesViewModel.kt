package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteListRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

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