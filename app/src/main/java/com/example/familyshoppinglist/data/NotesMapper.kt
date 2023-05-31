package com.example.familyshoppinglist.data

import com.example.familyshoppinglist.domain.entity.Note

class NotesMapper {

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
        date = noteDbModel.date,
        priority = noteDbModel.priority,
        isDone = noteDbModel.isDone
    )

    fun mapDbModelListToEntityList(noteDbModelList: List<NoteDbModel>) =
        noteDbModelList.map { mapDbModelToEntity(it) }
}