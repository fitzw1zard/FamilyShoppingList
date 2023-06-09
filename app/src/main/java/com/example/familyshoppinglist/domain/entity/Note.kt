package com.example.familyshoppinglist.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    var id: Int = UNDEFINED_ID,
    val text: String,
    val date: String,
    val priority: Int,
    val isDone: Boolean
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
        const val UNDEFINED_DATE =  "01.01.1991 00:00"
    }
}

