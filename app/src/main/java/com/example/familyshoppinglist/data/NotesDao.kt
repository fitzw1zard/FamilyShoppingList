package com.example.familyshoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.familyshoppinglist.domain.NoteItem
import io.reactivex.rxjava3.core.Completable


@Dao
interface NotesDao {

    @Query("SELECT * FROM notes ORDER BY priority DESC")
    fun getNoteList(): LiveData<List<NoteItem>>

    @Query("SELECT * FROM notes WHERE id == :id")
    fun getNoteItem(id: Int): LiveData<NoteItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(noteItem: NoteItem): Completable

    @Query("DELETE FROM notes WHERE id == :id")
    fun deleteNote(id: Int): Completable

    @Query("UPDATE notes SET isDone = :isDone WHERE id == :id")
    fun isDone(id: Int, isDone: Boolean): Completable

    @Query("UPDATE notes SET text = :text, priority = :priority WHERE id == :id")
    fun editNote (id: Int, text: String, priority: Int): Completable

}