package com.egadwys.notesApp.data

import com.egadwys.notesApp.data.todo.TodoDao
import com.egadwys.notesApp.data.todo.models.Todo
import kotlinx.coroutines.flow.Flow

class ToDoRepository(private val todoDao: TodoDao) {

    val todosFlow: Flow<List<Todo>>
        get() = todoDao.getTodos()


    suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun deleteAllTodos() {
        todoDao.deleteAllTodos()
    }

    fun searchTodo(searchQuery: String) =
        todoDao.searchTodo(searchQuery)

}