package com.egadwys.notesApp.ui.note.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.egadwys.notesApp.R
import com.egadwys.notesApp.data.note.models.Note
import com.egadwys.notesApp.data.note.models.Priority
import com.egadwys.notesApp.databinding.FragmentNoteUpdateBinding
import com.egadwys.notesApp.ui.note.NoteSharedViewModel
import com.egadwys.notesApp.ui.note.NoteViewModel

class NoteUpdateFragment : Fragment() {

    private var _binding: FragmentNoteUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<NoteUpdateFragmentArgs>()

    private val mNoteViewModel: NoteViewModel by viewModels()
    private val mNoteSharedViewModel: NoteSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteUpdateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.note = args.currentNote
        setupMenuUpdate()
        setupChip()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupMenuUpdate() {
        binding.apply {
            toolbarNoteUpdate.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            toolbarNoteUpdate.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.note_update_save -> {
                        updateNoteToDB()
                        true
                    }
                    R.id.note_update_delete -> {
                        deleteNote()
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
        binding.apply {
//            when (args.currentNote.Priority) {
//                Priority.LOW -> chipLowUpdate.isChecked = true
//                Priority.MEDIUM -> chipMediumUpdate.isChecked = true
//                Priority.HIGH -> chipHighUpdate.isChecked = true
//            }

            chipGroupNoteUpdate.setOnCheckedChangeListener { group, checkedId ->
                val titleOrNull = group.findViewById<Chip>(checkedId)?.text.toString()
                mNoteSharedViewModel.parsePriority(titleOrNull)
            }
        }
    }

    private fun updateNoteToDB() {
        binding.apply {
            val title = edtNoteUpdateTitle.text.toString()
            val content = edtNoteUpdateContent.text.toString()

            val validation = mNoteSharedViewModel.validationNoteForm(title, content)
            if (validation) {
                val noteUpdate = Note(
                    args.currentNote.id,
                    title,
                    content,
                    args.currentNote.date,
                    mNoteSharedViewModel.mPriority.value!!
                )
                mNoteViewModel.updateNote(noteUpdate)
                Toast.makeText(requireContext(), "Note updated", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please fill out fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setNegativeButton("No") { _, _ -> }
            setPositiveButton("Yes") { _, _ ->
                mNoteViewModel.deleteNote(args.currentNote)
                Toast.makeText(
                    requireContext(),
                    "Successfully to remove ${args.currentNote.title}",
                    Toast.LENGTH_LONG
                ).show()
                findNavController().popBackStack()
            }
            setTitle("Delete ${args.currentNote.title}")
            setMessage("Are you sure want to delete ${args.currentNote.title}?")
        }.show()
    }

}