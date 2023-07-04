package com.example.familyshoppinglist.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.familyshoppinglist.R
import com.example.familyshoppinglist.databinding.NoteItemBinding
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.utils.HIGH_PRIORITY
import com.example.familyshoppinglist.utils.LOW_PRIORITY
import com.example.familyshoppinglist.utils.MEDIUM_PRIORITY

class NotesAdapter(private val context: Context) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(
    object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
) {

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
        holder.bind(note)
    }

    inner class NotesViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            with(binding) {
                with(note) {
                    tvNote.text = text
                    tvDate.text = date
                    when (priority) {
                        LOW_PRIORITY -> if (isDone) {
                            cvNote.setCardBackgroundColor(
                                context.getColor(
                                    R.color.low_priority_done
                                )
                            )
                            cvNote.cardElevation = 0f
                        } else {
                            cvNote.setCardBackgroundColor(
                                context.getColor(
                                    R.color.low_priority
                                )
                            )
                            cvNote.cardElevation = 4f
                        }

                        MEDIUM_PRIORITY -> if (isDone) {
                            cvNote.setCardBackgroundColor(
                                context.getColor(
                                    R.color.medium_priority_done
                                )
                            )
                            cvNote.cardElevation = 0f
                        } else {
                            cvNote.setCardBackgroundColor(
                                context.getColor(
                                    R.color.medium_priority
                                )
                            )
                            cvNote.cardElevation = 4f
                        }

                        HIGH_PRIORITY -> if (isDone) {
                            cvNote.setCardBackgroundColor(
                                context.getColor(
                                    R.color.high_priority_done
                                )
                            )
                            cvNote.cardElevation = 0f
                        } else {
                            cvNote.setCardBackgroundColor(
                                context.getColor(
                                    R.color.high_priority
                                )
                            )
                            cvNote.cardElevation = 4f
                        }

                        else -> throw RuntimeException("Unknown priority : ${note.priority}")
                    }
                }
                root.setOnLongClickListener {
                    onNoteLongClickListener?.invoke(note)
                    true
                }
            }
        }
    }
}



