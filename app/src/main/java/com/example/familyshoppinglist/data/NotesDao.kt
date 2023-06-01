package com.example.familyshoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    fun getNotesList(): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE id == :id LIMIT 1")
    suspend fun getNote(id: Int): NoteDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDbModel: NoteDbModel)

    @Query("DELETE FROM notes WHERE id == :id")
    suspend fun deleteNote(id: Int)

    @Query("UPDATE notes SET isDone = :isDone WHERE id == :id")
    suspend fun isNoteDone(id: Int, isDone: Boolean)

}