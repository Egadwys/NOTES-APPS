package com.egadwys.notesApp.data.todo

import androidx.room.*
import com.egadwys.notesApp.data.todo.models.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY isDone ASC")
    fun getTodos(): Flow<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("DELETE FROM todo_table")
    suspend fun deleteAllTodos()

    @Query("SELECT * FROM todo_table WHERE :searchQuery")
    fun searchTodo(searchQuery: String): Flow<List<Todo>>
}