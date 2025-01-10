package com.example.todo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "details") val details: String
)
