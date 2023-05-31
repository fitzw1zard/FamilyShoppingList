package com.example.familyshoppinglist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.familyshoppinglist.databinding.FragmentNotesBinding
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.presentation.adapter.NotesAdapter
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel

class NotesFragment : Fragment() {

    private lateinit var notesAdapter: NotesAdapter

    private val viewModelFactory by lazy {
        NotesViewModelFactory(requireActivity().application, null)
    }
    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }

    private var _binding: FragmentNotesBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentNotesBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.buttonAddNote.setOnClickListener {
            launchChangeNoteFragmentForAdd()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchChangeNoteFragmentForAdd() {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToChangeNoteFragment(null)
        )
    }

    private fun launchChangeNoteFragmentForEdit(note: Note) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToChangeNoteFragment(note)
        )
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter()
        binding.rvNotes.adapter = notesAdapter
    }



}
