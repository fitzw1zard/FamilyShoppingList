package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.usecases.AddNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase

class NotesViewModel(
    private val application: Application,
    private val notes: List<NoteItem>
) : ViewModel() {

    private val repository = NoteListRepositoryImpl

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    val noteList = getNoteListUseCase.getNoteList()

    fun deleteNoteItem(noteItem: NoteItem) {
        deleteNoteUseCase.deleteNote(noteItem)
    }

    fun changeDoneState(noteItem: NoteItem) {
        val newItem = noteItem.copy(isDone = noteItem.isDone.not())
        addNoteUseCase.addNote(newItem)
    }

    fun addNoteItem(noteItem: NoteItem) {
        addNoteUseCase.addNote(noteItem)
    }

    fun editNote(noteItem: NoteItem) {
        editNoteUseCase.editNote(noteItem)
    }

}