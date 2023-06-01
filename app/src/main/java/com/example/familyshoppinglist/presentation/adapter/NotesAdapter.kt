package com.example.familyshoppinglist.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.familyshoppinglist.R
import com.example.familyshoppinglist.databinding.NoteItemBinding
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.utils.HIGH_PRIORITY
import com.example.familyshoppinglist.utils.LOW_PRIORITY
import com.example.familyshoppinglist.utils.MEDIUM_PRIORITY

class NotesAdapter(
    private val context: Context
) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NoteDiffCallback()) {

    var onShopItemLongClickListener: ((Note) -> Unit)? = null
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

        with(binding) {
            root.setOnLongClickListener {
                onShopItemLongClickListener?.invoke(note)
                true
            }
            tvNote.text = note.text

            when (note.priority) {
                LOW_PRIORITY -> if (note.isDone) {
                    cvNote.setCardBackgroundColor(context.getColor(R.color.low_priority_done))
                    cvNote.cardElevation = 0f
                } else {
                    cvNote.setCardBackgroundColor(context.getColor(R.color.low_priority))
                }


                MEDIUM_PRIORITY -> if (note.isDone) {
                    cvNote.setCardBackgroundColor(context.getColor(R.color.medium_priority_done))
                    cvNote.cardElevation = 0f
                } else {
                    cvNote.setCardBackgroundColor(context.getColor(R.color.medium_priority))
                }


                HIGH_PRIORITY -> if (note.isDone) {
                    cvNote.setCardBackgroundColor(context.getColor(R.color.high_priority_done))
                    cvNote.cardElevation = 0f
                } else {
                    cvNote.setCardBackgroundColor(context.getColor(R.color.high_priority))
                }
            }
        }
    }

    inner class NotesViewHolder(val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}




