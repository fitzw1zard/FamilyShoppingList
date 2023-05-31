package com.example.familyshoppinglist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.familyshoppinglist.domain.entity.Note

class NoteItemDiffCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}