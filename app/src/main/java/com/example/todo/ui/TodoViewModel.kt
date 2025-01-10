package com.example.todo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.Todo
import com.example.todo.database.TodoDao
import com.example.todo.database.TodoDetails
import com.example.todo.database.TodoDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TodoViewModel(private val todoDao: TodoDao,
    private val todoDetailsDao: TodoDetailsDao
    ):ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    init {
       getAllTasks()
    }
    private fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.getAllTasks().collect { taskList ->
                _todos.value = taskList
            }
        }
    }
     fun addTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.add(todo)
            val todoDetails = TodoDetails(
                todoId = todo.uid,
                description = "Initial Description",
                status = 0
            )
            todoDetailsDao.add(todoDetails)
        }
    }
     fun deleteTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.delete(todo)
        }
    }

}

