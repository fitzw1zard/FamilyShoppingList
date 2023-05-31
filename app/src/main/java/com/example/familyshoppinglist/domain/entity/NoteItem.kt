package com.example.familyshoppinglist.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


data class NoteItem(
    val id: Int,
    val text: String,
    val date: String,
    val priority: Int,
    val isDone: Boolean
)

