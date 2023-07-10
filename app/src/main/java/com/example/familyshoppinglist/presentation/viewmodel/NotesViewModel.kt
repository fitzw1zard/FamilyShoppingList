package com.example.familyshoppinglist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.AddNoteUseCase
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

    val notes = getNoteListUseCase.getNotes()

    private val _errorInput = MutableStateFlow(false)
    val errorInput = _errorInput.asSharedFlow()

    private val _shouldCloseEditScreen = MutableStateFlow(false)
    val shouldCloseEditScreen = _shouldCloseEditScreen.asSharedFlow()

    fun saveNote(
        inputId: Int,
        inputText: String,
        inputPriority: Int,
        inputState: Boolean,
    ) {
        val valid = validateInput(inputText)
        if (valid) {
            viewModelScope.launch {
                val note = Note(
                    id = inputId,
                    text = inputText,
                    priority = inputPriority,
                    date = getDate(),
                    isDone = inputState
                )
                addNoteUseCase.addNote(note)
                finishWork()
            }

        }
    }

    fun changeNoteState(note: Note) {
        viewModelScope.launch {
            val changedNote = note.copy(
                isDone = note.isDone.not(),
                date = getDate()
            )
            editNoteUseCase.editNote(changedNote)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(note)
        }
    }

    fun resetErrorInputName() {
        _errorInput.value = false
    }

    private fun validateInput(inputText: String): Boolean {
        var result = true
        if (inputText.isBlank()) {
                _errorInput.value = inputText.isBlank()
            result = false
        }
        return result
    }


    private fun getDate(): String {
        return java.util.Calendar.getInstance().time.toString()
    }

    private fun finishWork() {
        _shouldCloseEditScreen.value = true
    }

}