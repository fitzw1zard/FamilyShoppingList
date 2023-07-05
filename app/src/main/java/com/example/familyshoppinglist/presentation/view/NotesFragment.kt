package com.example.familyshoppinglist.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.familyshoppinglist.MainApp
import com.example.familyshoppinglist.databinding.FragmentNotesBinding
import com.example.familyshoppinglist.domain.entity.Note
import com.example.familyshoppinglist.presentation.adapter.NotesAdapter
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel
import com.example.familyshoppinglist.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var notesAdapter: NotesAdapter

    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as MainApp).component
    }


    private var _binding: FragmentNotesBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentNotesBinding is null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        observeViewModel()
        binding.buttonAddNote.setOnClickListener {
            launchChangeNoteFragmentForAdd()
        }

    }

    private fun observeViewModel() {
       lifecycleScope.launch {
           repeatOnLifecycle(Lifecycle.State.RESUMED) {
               viewModel.notes.collect {
                   notesAdapter.submitList(it)
               }
           }
       }
    }

    private fun setupRecyclerView() {
        notesAdapter = NotesAdapter(requireContext())
        binding.rvNotes.adapter = notesAdapter

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
        notesAdapter.onNoteLongClickListener = {
            viewModel.changeNoteState(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
