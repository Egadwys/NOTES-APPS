package com.egadwys.notesApp.ui.todo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.egadwys.notesApp.data.todo.models.Todo
import com.egadwys.notesApp.databinding.ItemRowToDoBinding

class TodoListAdapter : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    var todoList = emptyList<Todo>()
    var listener: ((Todo, Boolean) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemRowToDoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todo: Todo) {
            binding.todo = todo
            binding.executePendingBindings()
        }

        init {
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                val todo = todoList[adapterPosition]
                listener?.invoke(todo, isChecked)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRowToDoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun saveTodo(todos: List<Todo>) {
        val todoDiffUtil = TodoDiffUtil(todoList, todos)
        val todoDiffResult = DiffUtil.calculateDiff(todoDiffUtil)
        this.todoList = todos
        todoDiffResult.dispatchUpdatesTo(this)
    }
}