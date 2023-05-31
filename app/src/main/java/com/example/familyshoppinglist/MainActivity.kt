package com.example.familyshoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NotesViewModel

    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        viewModel.noteList.observe(this) {
            Log.d("TEST_OF_LOADING", "Success: $it")
            if (count == 0) {
                count++
                val item = it.first()
                viewModel.changeDoneState(item)
            }
        }
    }
}