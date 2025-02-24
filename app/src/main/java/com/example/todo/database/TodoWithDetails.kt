package com.example.todo.database

import androidx.room.ColumnInfo

data class TodoWithDetails(
    @ColumnInfo(name = "title") val title: String,
    val id: Long,
    val todoId: Long,
    val description: String,
    val status: Int
)