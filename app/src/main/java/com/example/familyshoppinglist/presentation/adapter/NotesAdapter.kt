package com.example.familyshoppinglist.presentation.adapter

import android.content.Context
import android.util.Log
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

    var onNoteLongClickListener: ((Note) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(
            inflater,
            parent,
            false
        )
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        val binding = holder.binding
        Log.d("NotesAdapter", "onBindViewHolder")
        holder.bind(note)

    }


    inner class NotesViewHolder(val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.tvNote.text = note.text
            with(binding) {
                when (note.priority) {
                    LOW_PRIORITY -> if (note.isDone) {
                        cvNote.setCardBackgroundColor(context.getColor(R.color.low_priority_done))
                        cvNote.cardElevation = 0f
                        Log.d(
                            "NotesAdapter",
                            "color: ${context.getColor(R.color.low_priority_done)}"
                                    + "priority: ${note.isDone}"
                        )
                    } else {
                        cvNote.setCardBackgroundColor(context.getColor(R.color.low_priority))
                        cvNote.cardElevation = 4f
                        Log.d(
                            "NotesAdapter",
                            "color: ${context.getColor(R.color.low_priority_done)}"
                                    + "priority: ${note.isDone}"
                        )
                    }

                    MEDIUM_PRIORITY -> if (note.isDone) {
                        cvNote.setCardBackgroundColor(context.getColor(R.color.medium_priority_done))
                        cvNote.cardElevation = 0f
                        Log.d(
                            "NotesAdapter",
                            "color: ${context.getColor(R.color.low_priority_done)}"
                                    + "priority: ${note.isDone}"
                        )
                    } else {
                        cvNote.setCardBackgroundColor(context.getColor(R.color.medium_priority))
                        cvNote.cardElevation = 4f
                        Log.d(
                            "NotesAdapter",
                            "color: ${context.getColor(R.color.low_priority_done)}"
                                    + "priority: ${note.isDone}"
                        )
                    }

                    HIGH_PRIORITY -> if (note.isDone) {
                        cvNote.setCardBackgroundColor(context.getColor(R.color.high_priority_done))
                        cvNote.cardElevation = 0f
                        Log.d(
                            "NotesAdapter",
                            "color: ${context.getColor(R.color.low_priority_done)}"
                                    + "priority: ${note.isDone}"
                        )
                    } else {
                        cvNote.setCardBackgroundColor(context.getColor(R.color.high_priority))
                        cvNote.cardElevation = 4f
                        Log.d(
                            "NotesAdapter",
                            "color: ${context.getColor(R.color.low_priority_done)}"
                                    + "priority: ${note.isDone}"
                        )
                    }

                    else -> throw RuntimeException("Unknown priority : ${note.isDone}")
                }
                root.setOnLongClickListener() {
                    onNoteLongClickListener?.invoke(note)
                    true
                }
            }
        }
    }
}



