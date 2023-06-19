package com.example.familyshoppinglist.di

import android.app.Application
import com.example.familyshoppinglist.data.AppDatabase
import com.example.familyshoppinglist.data.NoteListRepositoryImpl
import com.example.familyshoppinglist.data.NotesDao
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindNoteListRepository(noteListRepositoryImpl: NoteListRepositoryImpl): NoteListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideNotesDao(
            application: Application
        ): NotesDao {
            return AppDatabase.getInstance(application).notesDao()
        }
    }


}