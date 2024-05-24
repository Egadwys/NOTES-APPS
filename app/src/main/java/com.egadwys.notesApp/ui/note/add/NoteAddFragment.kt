package com.egadwys.notesApp.ui.note.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.egadwys.notesApp.R
import com.egadwys.notesApp.data.note.models.Note
import com.egadwys.notesApp.databinding.FragmentNoteAddBinding
import com.egadwys.notesApp.ui.note.NoteSharedViewModel
import com.egadwys.notesApp.ui.note.NoteViewModel

class NoteAddFragment : Fragment() {

    private var _binding: FragmentNoteAddBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()
    private val noteSharedViewModel: NoteSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        setupChip()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupMenu() {
        binding.apply {
            toolbarNoteAdd.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            toolbarNoteAdd.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.note_add_save -> {
                        insertNoteToDB()
                        true
                    }
                    else -> {
                        super.onOptionsItemSelected(item)
                    }
                }
            }
        }
    }

    private fun setupChip() {
        binding.chipGroupNoteAdd.setOnCheckedChangeListener { group, checkedId ->
            val titleOrNull = group.findViewById<Chip>(checkedId)?.text.toString()
            noteSharedViewModel.parsePriority(titleOrNull)
        }
    }

    private fun insertNoteToDB() {
        binding.apply {
            val mTitle = edtNoteUpdateTitle.text.toString()
            val mContent = edtNoteUpdateContent.text.toString()

            val validation = noteSharedViewModel.validationNoteForm(mTitle, mContent)
            if (validation) {
                val note = Note(
                    0,
                    mTitle,
                    mContent,
                    noteSharedViewModel.getCurrentDate(),
                    noteSharedViewModel.mPriority.value!!
                )
                noteViewModel.insertData(note)
                findNavController().popBackStack()
                Toast.makeText(requireContext(), "New note added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Please fill the field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}