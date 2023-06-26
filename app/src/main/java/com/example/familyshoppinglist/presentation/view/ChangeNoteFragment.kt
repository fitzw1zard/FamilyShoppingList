package com.example.familyshoppinglist.presentation.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.familyshoppinglist.MainApp
import com.example.familyshoppinglist.R
import com.example.familyshoppinglist.databinding.FragmentChangeNoteBinding
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel
import com.example.familyshoppinglist.presentation.viewmodel.ViewModelFactory
import com.example.familyshoppinglist.utils.ERROR_PRIORITY
import com.example.familyshoppinglist.utils.HIGH_PRIORITY
import com.example.familyshoppinglist.utils.LOW_PRIORITY
import com.example.familyshoppinglist.utils.MEDIUM_PRIORITY
import javax.inject.Inject


class ChangeNoteFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val argsNote by navArgs<ChangeNoteFragmentArgs>()

    private var _binding: FragmentChangeNoteBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentChangeNoteBinding is null")


    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as MainApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTextChangeListeners()
        setupClickListeners()
        observeViewModel()
        editedNoteStatus()
    }


    private fun editedNoteStatus() {
        val editedNote = argsNote.note
        binding.etText.setText(editedNote?.text)
        when (editedNote?.priority) {
            LOW_PRIORITY -> binding.rbLow.isChecked = true
            MEDIUM_PRIORITY -> binding.rbMedium.isChecked = true
            HIGH_PRIORITY -> binding.rbHigh.isChecked = true
        }
    }

    private fun getPriority(): Int {
        return when (binding.radioGroupPriority.checkedRadioButtonId) {
            binding.rbLow.id -> LOW_PRIORITY
            binding.rbMedium.id -> MEDIUM_PRIORITY
            binding.rbHigh.id -> HIGH_PRIORITY
            else -> ERROR_PRIORITY
        }
    }


    private fun setupClickListeners() {
        with(binding) {
            buttonSave.setOnClickListener {
                viewModel.saveNote(
                    inputId = argsNote.note?.id ?: 0,
                    inputText = etText.text.toString(),
                    inputPriority = getPriority(),
                    inputState = argsNote.note?.isDone ?: false,
                )
            }
            buttonUndo.setOnClickListener {
                closeScreen()
            }
        }

    }

    private fun observeViewModel() {
        with(viewModel) {

            shouldCloseScreen.observe(viewLifecycleOwner) {
                closeScreen()
            }
            errorInputName.observe(viewLifecycleOwner) {
                if (it) {
                    binding.tilText.error = getString(R.string.error_empty_note)
                }
            }
        }
    }


    private fun addTextChangeListeners() {
        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun closeScreen() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}