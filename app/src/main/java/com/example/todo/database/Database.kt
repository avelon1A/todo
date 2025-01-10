package com.example.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Todo::class, TodoDetails::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
    abstract fun todoDetails():TodoDetailsDao
}