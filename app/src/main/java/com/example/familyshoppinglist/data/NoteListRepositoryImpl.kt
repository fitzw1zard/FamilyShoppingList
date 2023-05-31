package com.example.familyshoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.map
import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import java.util.Date

class NoteListRepositoryImpl(application: Application) : NoteListRepository {

    private val notesDao = AppDatabase.getInstance(application).notesDao()
    private val mapper = NotesMapper()

    override fun addNote(noteItem: NoteItem) {
        notesDao.addNote(mapper.mapEntityToDbModel(noteItem))
    }

    override fun editNote(noteItem: NoteItem) {
        notesDao.addNote(mapper.mapEntityToDbModel(noteItem))
    }

    override fun deleteNote(noteItem: NoteItem) {
        notesDao.deleteNote(noteItem.id)
    }

    override fun getNoteItem(id: Int): NoteItem =
        mapper.mapDbModelToEntity(notesDao.getNoteItem(id))


    override fun getNoteList(): LiveData<List<NoteItem>> =
        MediatorLiveData<List<NoteItem>>().apply {
            addSource(notesDao.getNotesList()) {
                value = mapper.mapDbModelListToEntityList(it)
            }
        }

}