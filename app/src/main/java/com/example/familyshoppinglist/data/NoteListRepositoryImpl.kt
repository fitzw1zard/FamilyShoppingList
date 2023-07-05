package com.example.familyshoppinglist.data

import android.util.Log
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteListRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao,
    private val mapper: NotesMapper
) : NoteListRepository {

    override suspend fun addNote(note: Note) {
        notesDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun editNote(note: Note) {
        notesDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note.id)
    }

    override suspend fun getNote(id: Int): Note =
        mapper.mapDbModelToEntity(notesDao.getNote(id))


    override fun getNotes(): Flow<List<Note>> =
        notesDao.getNotesList().map {
            mapper.mapDbModelListToEntityList(it)
        }
}



