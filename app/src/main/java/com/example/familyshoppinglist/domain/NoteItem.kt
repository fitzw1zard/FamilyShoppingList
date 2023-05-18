package com.example.familyshoppinglist.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteItem(
    @PrimaryKey(autoGenerate = true)
    val text: String,
    val date: String,
    val priority: Int,
    var isDone: Boolean,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val PRIORITY_HIGH = 3
        const val PRIORITY_MEDIUM = 2
        const val PRIORITY_LOW = 1
        const val UNDEFINED_ID = -1
    }
}

