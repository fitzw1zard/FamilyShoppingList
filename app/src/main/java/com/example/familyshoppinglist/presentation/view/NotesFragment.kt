package com.example.familyshoppinglist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.familyshoppinglist.databinding.FragmentNotesBinding
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.presentation.adapter.NotesAdapter
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel

class NotesFragment : Fragment() {

    private lateinit var notesAdapter: NotesAdapter

    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this)[NotesViewModel::class.java]
    }

    private var _binding: FragmentNotesBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentNotesBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonAddNote.setOnClickListener {
            launchChangeNoteFragmentForAdd()
        }

    }

    private fun setupRecyclerView() {
        viewModel.noteList.observe(viewLifecycleOwner) {
            notesAdapter.submitList(it)
        }
        with(binding.rvNotes) {
            notesAdapter = NotesAdapter(requireContext())
            adapter = notesAdapter
        }
        setupLongClickListener()
        setupSwipeListener(binding.rvNotes)
    }

    private fun setupSwipeListener(rvNotes: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                rvNotes: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                val note = notesAdapter.currentList[viewHolder.adapterPosition]
                when (direction) {

                    ItemTouchHelper.RIGHT -> {
                        launchChangeNoteFragmentForEdit(note)
                    }

                    ItemTouchHelper.LEFT -> {
                        viewModel.deleteNote(note)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvNotes)
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

    private fun setupLongClickListener() {
        notesAdapter.onShopItemLongClickListener = {
            viewModel.changeNoteState(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
