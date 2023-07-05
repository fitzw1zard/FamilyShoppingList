package com.example.familyshoppinglist.domain.repository

import com.example.familyshoppinglist.domain.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteListRepository {

    suspend fun addNote (note: Note)

    suspend fun editNote (note: Note)

    suspend fun deleteNote (note: Note)

    suspend fun getNote (id: Int): Note

    fun getNotes (): Flow<List<Note>>


}