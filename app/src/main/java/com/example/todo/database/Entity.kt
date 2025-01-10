package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "details") val details: String
)


@Entity(tableName = "todoDetails",
    foreignKeys = [
        ForeignKey(
            entity = Todo::class,
            parentColumns = ["uid"],
            childColumns = ["todoId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TodoDetails(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "todoId") val todoId: UUID,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "status") val status: Int =0
)
