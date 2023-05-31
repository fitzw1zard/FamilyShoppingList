package com.example.familyshoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val text: String,
    val date: String,
    val priority: Int,
    val isDone: Boolean

)