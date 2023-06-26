package com.example.familyshoppinglist.di

import androidx.lifecycle.ViewModel
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel

}