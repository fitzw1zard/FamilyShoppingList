package com.example.familyshoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.familyshoppinglist.domain.entity.NoteItem
import com.example.familyshoppinglist.domain.repository.NoteListRepository
import java.util.Comparator
import java.util.Date

object NoteListRepositoryImpl : NoteListRepository {

    private val noteListLD = MutableLiveData<List<NoteItem>>()
    private val noteList = sortedSetOf(Comparator<NoteItem> { o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for ( i in 0 until 10) {
            addNote(
                NoteItem(
                    "Note $i",
                    Date().toString(),
                    (1..3).random(),
                    false
                )
            )
        }
    }

    override fun addNote(noteItem: NoteItem) {
        if (noteItem.id == NoteItem.UNDEFINED_ID) {
            noteItem.id = autoIncrementId++
        }
        noteList.add(noteItem)
        updateList()
    }

    override fun editNote(noteItem: NoteItem) {
        val oldElement = getNoteItem(noteItem.id)
        noteList.remove(oldElement)
        addNote(noteItem)

    }

    override fun deleteNote(noteItem: NoteItem) {
        noteList.remove(noteItem)
        updateList()
    }

    override fun getNoteItem(id: Int): NoteItem {
        return noteList.first { it.id == id }
    }

    override fun getNoteList(): LiveData<List<NoteItem>> {
        return noteListLD
    }

    private fun updateList() {
        noteListLD.value = noteList.toList()
    }
}