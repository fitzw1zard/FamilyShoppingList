package com.example.familyshoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotesList(): LiveData<List<NoteItemDbModel>>

    @Query("SELECT * FROM notes WHERE id == :id LIMIT 1")
    fun getNoteItem(id: Int): NoteItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteItemDbModel: NoteItemDbModel): NoteItemDbModel

    @Query("DELETE FROM notes WHERE id == :id")
    fun deleteNote(id: Int)

    @Query("UPDATE notes SET isDone = :isDone WHERE id == :id")
    fun isNoteDone(id: Int, isDone: Boolean)

}