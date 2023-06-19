package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.AddNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {


    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun saveNote(
        inputId: Int,
        inputText: String,
        inputPriority: Int,
        inputState: Boolean
    ) {
        val valid = validateInput(inputText)
        if (valid) {
            viewModelScope.launch {
                val note = Note(
                    id = inputId,
                    text = inputText,
                    priority = inputPriority,
                    isDone = inputState
                )
                addNoteUseCase.addNote(note)
                finishWork()
            }

        }
    }

    fun getNote(noteId: Int) {
        viewModelScope.launch {
            getNoteUseCase.getNote(noteId)
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

}