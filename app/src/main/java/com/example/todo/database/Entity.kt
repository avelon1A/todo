package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "details") val details: String
)

@Entity(tableName = "todoDetails",
    foreignKeys = [
        ForeignKey(
            entity = Todo::class,
            parentColumns = ["id"],
            childColumns = ["todoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TodoDetails(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "todoId") val todoId: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "status") val status: Int = 0
)
