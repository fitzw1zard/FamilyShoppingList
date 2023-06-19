package com.example.familyshoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository
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



    override fun getNotes(): LiveData<List<Note>> =
        MediatorLiveData<List<Note>>().apply {
            addSource(notesDao.getNotesList()) {
                value = mapper.mapDbModelListToEntityList(it)
            }
        }

}