package com.example.familyshoppinglist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY isDone ASC, priority DESC, date DESC")
    fun getNotesList(): Flow<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE id == :id LIMIT 1")
    suspend fun getNote(id: Int): NoteDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteDbModel: NoteDbModel)

    @Query("DELETE FROM notes WHERE id == :id")
    suspend fun deleteNote(id: Int)

    @Query("UPDATE notes SET isDone = :isDone WHERE id == :id")
    suspend fun isNoteDone(id: Int, isDone: Boolean)

}