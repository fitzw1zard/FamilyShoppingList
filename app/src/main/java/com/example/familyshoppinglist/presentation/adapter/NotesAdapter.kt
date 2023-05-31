package com.example.familyshoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.familyshoppinglist.R
import com.example.familyshoppinglist.databinding.NoteItemBinding
import com.example.familyshoppinglist.domain.entity.Note

class NotesAdapter : ListAdapter<Note, NotesViewHolder>(NoteItemDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
         val binding = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        val binding = holder.binding




        if (note.isDone) {
            binding.root.alpha = 0.5f
        } else {
            binding.root.alpha = 1f
        }
    }

}