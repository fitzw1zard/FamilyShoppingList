package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteListRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val noteList = getNoteListUseCase.getNoteList()


    fun changeNoteState(note: Note) {
       scope.launch {
           note.isDone = note.isDone.not()
           editNoteUseCase.editNote(note)
       }
    }

    fun deleteNote(note: Note) {
        scope.launch {
            deleteNoteUseCase.deleteNote(note)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}