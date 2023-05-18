package com.example.familyshoppinglist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.familyshoppinglist.domain.NoteItem

@Database(entities = [NoteItem::class], version = 1, exportSchema = false)
abstract class NotesDatabase: RoomDatabase() {

    companion object {
        private var db: NotesDatabase? = null
        private const val DB_NAME = "noteDatabase"
        private val LOCK = Any()


        fun getInstance(context: Context): NotesDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                return Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    DB_NAME
                ).build()
            }
        }
    }

    abstract fun notesDao(): NotesDao
}