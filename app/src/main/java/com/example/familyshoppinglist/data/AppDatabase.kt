package com.example.familyshoppinglist.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.familyshoppinglist.domain.entity.Note

@Database(entities = [NoteDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "notesDatabase"
        private val LOCK = Any()

        fun getInstance(application: Application): AppDatabase {
            db?.let {
                return it
            }
            synchronized(LOCK) {
                db?.let {
                    return it
                }
                val instance = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
                db = instance
                return instance
            }
        }
    }

}