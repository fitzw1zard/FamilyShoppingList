package com.example.familyshoppinglist.data

import com.example.familyshoppinglist.domain.entity.NoteItem

class NotesMapper {

    fun mapEntityToDbModel(noteItem: NoteItem) = NoteItemDbModel(
        id = noteItem.id,
        text = noteItem.text,
        date = noteItem.date,
        priority = noteItem.priority,
        isDone = noteItem.isDone
    )


    fun mapDbModelToEntity(noteItemDbModel: NoteItemDbModel) = NoteItem(
        id = noteItemDbModel.id,
        text = noteItemDbModel.text,
        date = noteItemDbModel.date,
        priority = noteItemDbModel.priority,
        isDone = noteItemDbModel.isDone
    )

    fun mapDbModelListToEntityList(noteItemDbModelList: List<NoteItemDbModel>) =
        noteItemDbModelList.map { mapDbModelToEntity(it) }
}