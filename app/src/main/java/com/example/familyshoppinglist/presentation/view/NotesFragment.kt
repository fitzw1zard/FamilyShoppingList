package com.example.familyshoppinglist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.familyshoppinglist.databinding.FragmentNotesBinding
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.presentation.adapter.NotesAdapter
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel


class NotesFragment : Fragment() {

    private val viewModelFactory: NotesViewModel by lazy {
        NotesViewModelFactory(requireActivity().application, null)
    }



    private lateinit var notesAdapter: NotesAdapter
//    private lateinit var viewmodel: NotesViewModel

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
//        viewmodel = ViewModelProvider(this.requireActivity(), viewModelFactory)[NotesViewModel::class.java]
        binding.buttonAddNote.setOnClickListener {
            launchNotesFragment(Note.EMPTY)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchNotesFragment(note: Note) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToChangeNoteFragment(note)
        )
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter()
        binding.rvNotes.adapter = notesAdapter
    }


}
