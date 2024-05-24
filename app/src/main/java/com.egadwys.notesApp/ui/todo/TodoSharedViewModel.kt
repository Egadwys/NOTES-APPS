package com.egadwys.notesApp.ui.todo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.egadwys.notesApp.data.todo.models.Todo

class TodoSharedViewModel(application: Application) : AndroidViewModel(application) {

    val emptyTodos: MutableLiveData<Boolean> = MutableLiveData()

    fun checkTodosIfEmpty(todos: List<Todo>) {
        emptyTodos.value = todos.isEmpty()
    }

}