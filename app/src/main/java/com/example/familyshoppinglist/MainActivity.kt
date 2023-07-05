package com.example.familyshoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
