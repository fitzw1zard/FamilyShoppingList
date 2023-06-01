package com.example.familyshoppinglist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.familyshoppinglist.databinding.NoteItemBinding
import com.example.familyshoppinglist.domain.entity.Note

class NotesAdapter : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NoteDiffCallback()) {


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
        holder.tvNote.text = note.text
        if (note.isDone) {
            holder.cvNote.alpha = 0.5f
        } else {
            holder.cvNote.alpha = 1f
        }
    }

    inner class NotesViewHolder(binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvNote = binding.tvNote
        val cvNote = binding.cvNote
    }

}