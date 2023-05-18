package com.example.familyshoppinglist.domain

data class NoteItem(
    val id: Int,
    val text: String,
    val date: String,
    val priority: Int,
    val isDone: Boolean,
)

