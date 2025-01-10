package com.example.todo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.database.TodoDetails
import com.example.todo.database.TodoDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TodoDetailsViewModel(private val todoDetailsDao: TodoDetailsDao):ViewModel() {
    private val _todosDetails= MutableStateFlow<TodoDetails?>( null)
    val todosDetails: StateFlow<TodoDetails?> = _todosDetails.asStateFlow()

    fun getTodoDetails(todoId: UUID){
        viewModelScope.launch(Dispatchers.IO) {
            todoDetailsDao.getAllTasks(todoId).collect { taskDetails ->
                _todosDetails.value = taskDetails
            }
        }
    }
    fun updateStatus(todoDetailsId: UUID, status: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDetailsDao.updateStatusById(todoDetailsId, status)
        }

    }
}