package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.usecases.AddNoteUseCase
import com.example.familyshoppinglist.domain.usecases.EditNoteUseCase
import com.example.familyshoppinglist.domain.usecases.GetNoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ChangeNoteViewModel (
    private val application: Application,
    private val argsNote: Note?
) : ViewModel() {

    private val repository = NoteListRepositoryImpl(application)

    private val addNoteUseCase = AddNoteUseCase(repository)
    private val editNoteUseCase = EditNoteUseCase(repository)
    private val getNoteUseCase = GetNoteUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName
    private val _note = MutableLiveData<Note?>()
    val notes: LiveData<Note?>
        get() = _note
    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    init {
        if (argsNote != null) {
            _note.value = argsNote
        }
    }

    fun addNote(inputText: String, inputPriority: Int) {
        val valid = validateInput(inputText)
        if (valid) {
            scope.launch {
            val note = Note(
                text = inputText,
                priority = inputPriority,
//                date = inputDate
            )

                addNoteUseCase.addNote(note)
            }
            finishWork()
        }
    }

    fun getNote(noteId: Int) {
        scope.launch {
            val note = getNoteUseCase.getNote(noteId)
            _note.postValue(note)
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

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}