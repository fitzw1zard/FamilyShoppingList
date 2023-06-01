package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.AddNoteUseCase
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NotesViewModel(
    private val application: Application,
    private val argsNote: Note?
) : ViewModel() {

    private val repository = NoteListRepositoryImpl(application)

    private val getNoteListUseCase = GetNoteListUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val noteList = getNoteListUseCase.getNoteList()

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private val _note = MutableLiveData<Note?>()
    val notes: LiveData<Note?>
        get() = _note
    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun addNote(inputText: String, inputPriority: Int) {
        val valid = validateInput(inputText)
        if (valid) {
            val note = Note(
                text = inputText,
                priority = inputPriority,
//                date = inputDate
            )
            scope.launch {
                addNoteUseCase.addNote(note)
            }
            finishWork()
        }
    }

    fun editNote(
        inputText: String,
        inputPriority: Int,
        inputDate: String
    ) {
        addNote(
            inputText,
            inputPriority,
//            inputDate
        )
    }

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

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    private fun validateInput(inputText: String): Boolean {
        var result = true
        if (inputText.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        return result
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

    init {
        if (argsNote != null) {
            _note.value = argsNote
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}