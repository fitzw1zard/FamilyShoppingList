package com.example.familyshoppinglist.presentation.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel

class NotesViewModelFactory(
    private val application: Application,
    private val argsNote: Note?
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(application, argsNote) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}