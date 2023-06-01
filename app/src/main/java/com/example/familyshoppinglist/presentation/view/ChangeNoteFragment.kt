package com.example.familyshoppinglist.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.familyshoppinglist.databinding.FragmentChangeNoteBinding
import com.example.familyshoppinglist.presentation.viewmodel.NotesViewModel
import com.example.familyshoppinglist.utils.ERROR_PRIORITY
import com.example.familyshoppinglist.utils.HIGH_PRIORITY
import com.example.familyshoppinglist.utils.LOW_PRIORITY
import com.example.familyshoppinglist.utils.MEDIUM_PRIORITY


class ChangeNoteFragment : Fragment() {

    private val argsNote by navArgs<ChangeNoteFragmentArgs>()

    private var _binding: FragmentChangeNoteBinding? = null

    private val viewModelFactory by lazy {
        NotesViewModelFactory(requireActivity().application, null)
    }
    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NotesViewModel::class.java]
    }

    private val binding
        get() = _binding ?: throw IllegalStateException("FragmentChangeNoteBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        addTextChangeListeners()

        with(binding) {
            buttonSave.setOnClickListener {
                viewModel.addNote(
                    etText.text.toString(),
                    getPriority()
                    )
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}