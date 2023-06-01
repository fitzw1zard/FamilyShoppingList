package com.example.familyshoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.domain.repository.NoteListRepository

class NoteListRepositoryImpl(application: Application) : NoteListRepository {

    private val notesDao = AppDatabase.getInstance(application).notesDao()
    private val mapper = NotesMapper()

    override suspend fun addNote(note: Note) {
        notesDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun editNote(note: Note) {
        notesDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note.id)
    }

    override suspend fun getNoteItem(id: Int): Note =
        mapper.mapDbModelToEntity(notesDao.getNoteItem(id))


    override fun getNoteList(): LiveData<List<Note>> =
        MediatorLiveData<List<Note>>().apply {
            addSource(notesDao.getNotesList()) {
                value = mapper.mapDbModelListToEntityList(it)
            }
        }

}