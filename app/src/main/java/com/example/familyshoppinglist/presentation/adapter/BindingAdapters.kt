package com.example.familyshoppinglist.presentation.adapter

import androidx.databinding.BindingAdapter
import com.example.familyshoppinglist.R
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("errorInputName")
fun bindErrorInputText(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.error_input_note)
    } else {
        null
    }
    textInputLayout.error = message
}

