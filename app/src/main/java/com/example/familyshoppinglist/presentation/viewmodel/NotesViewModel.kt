package com.example.familyshoppinglist.presentation.viewmodel

import android.icu.text.SimpleDateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.AddNoteUseCase
import com.example.familyshoppinglist.domain.usecases.DeleteNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteListUseCase
import kotlinx.coroutines.launch
import java.util.Date
import java.util.Locale

import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteListUseCase: GetNoteListUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val editNoteUseCase: EditNoteUseCase
) : ViewModel() {

    val notes = getNoteListUseCase.getNoteList()


    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(note)
        }
    }

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun changeNoteState(note: Note) {
        viewModelScope.launch {
            val changedNote = note.copy(
                isDone = note.isDone.not(),
                date = getDate()
            )
            editNoteUseCase.editNote(changedNote)
        }
    }


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


    private fun getDate(): String {
        return java.util.Calendar.getInstance().time.toString()
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}