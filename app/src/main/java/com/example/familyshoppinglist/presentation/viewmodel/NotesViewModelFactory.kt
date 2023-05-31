package com.example.familyshoppinglist.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.familyshoppinglist.domain.entity.NoteItem

class NotesViewModelFactory(
    private val application: Application,
    private val notes: List<NoteItem>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(application, notes) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}
