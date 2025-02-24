package com.example.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.TodoDetails
import com.example.todo.database.TodoDetailsDao
import com.example.todo.database.TodoWithDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoDetailsViewModel(private val todoDetailsDao: TodoDetailsDao):ViewModel() {
    private val _todosDetails= MutableStateFlow<TodoWithDetails?>( null)
    val todosDetails: StateFlow<TodoWithDetails?> = _todosDetails.asStateFlow()

    fun getTodoDetails(todoId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            todoDetailsDao.getTodoWithDetailsByTodoId(todoId).collect { taskDetails ->
                _todosDetails.value = taskDetails
            }
        }
    }
    fun updateStatus(todoDetailsId: Long, status: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDetailsDao.updateStatusById(todoDetailsId, status)
        }

    }
}