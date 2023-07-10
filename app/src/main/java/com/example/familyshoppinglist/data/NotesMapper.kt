package com.example.familyshoppinglist.data

import android.icu.text.SimpleDateFormat
import com.example.familyshoppinglist.domain.entity.Note
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class NotesMapper @Inject constructor() {

    fun mapEntityToDbModel(note: Note) = NoteDbModel(
        id = note.id,
        text = note.text,
        date = note.date,
        priority = note.priority,
        isDone = note.isDone
    )


    fun mapDbModelToEntity(noteDbModel: NoteDbModel) = Note(
        id = noteDbModel.id,
        text = noteDbModel.text,
        date = formatDate(noteDbModel.date),
        priority = noteDbModel.priority,
        isDone = noteDbModel.isDone
    )

    fun mapDbModelListToEntityList(noteDbModelList: List<NoteDbModel>) =
        noteDbModelList.map {
            mapDbModelToEntity(it)
        }

    private fun formatDate(dateString: String): String {
        val fullDateFormatter = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
        val date = fullDateFormatter.parse(dateString)
        val dateFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formattedDate = dateFormatter.format(date)
        val formattedTime = timeFormatter.format(date)
        return "$formattedDate $formattedTime"
    }

}