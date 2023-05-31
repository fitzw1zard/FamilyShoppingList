package com.example.familyshoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.familyshoppinglist.domain.entity.NoteItem

class NotesAdapter : ListAdapter<NoteItem, NotesViewHolder>(NoteItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), viewType, parent, false
        )
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        val binding = holder.binding

        if (note.isDone) {

        } else {

        }
    }

}