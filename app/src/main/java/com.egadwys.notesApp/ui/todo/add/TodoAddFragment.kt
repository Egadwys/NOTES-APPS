package com.egadwys.notesApp.ui.todo.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.egadwys.notesApp.data.todo.models.Todo
import com.egadwys.notesApp.databinding.FragmentTodoAddBinding
import com.egadwys.notesApp.ui.todo.TodoSharedViewModel
import com.egadwys.notesApp.ui.todo.TodoViewModel


class TodoAddFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentTodoAddBinding? = null
    private val binding get() = _binding!!

    private val mTodoViewModel: TodoViewModel by viewModels()
    private val mTodoSharedViewModel: TodoSharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertTodo()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun insertTodo() {
        binding.apply {
            btnTodoAdd.setOnClickListener {
                val mTitle = edtTodoAddTitle.text.toString()
                if (mTitle.isNotEmpty()) {
                    val todo = Todo(
                        0,
                        mTitle
                    )
                    mTodoViewModel.insertTodo(todo)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(requireContext(), "Please enter the task", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}