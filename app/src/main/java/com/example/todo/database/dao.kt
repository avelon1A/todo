package com.example.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao{
    @Query("SELECT * FROM todo")
    fun getAllTasks(): Flow<List<Todo>>
    @Insert
    fun add(todo:Todo)
    @Delete
    fun delete(todo:Todo)


}