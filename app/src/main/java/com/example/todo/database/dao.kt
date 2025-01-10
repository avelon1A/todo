package com.example.todo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TodoDao{
    @Query("SELECT * FROM todo")
    fun getAllTasks(): Flow<List<Todo>>
    @Insert
    fun add(todo:Todo)
    @Delete
    fun delete(todo:Todo)

}

@Dao
interface TodoDetailsDao{

    @Insert
    fun add(todoDetails: TodoDetails)

    @Query("SELECT * FROM todoDetails WHERE todoId = :todoId")
    fun getAllTasks(todoId: UUID): Flow<TodoDetails>

    @Query("UPDATE todoDetails SET status = :status WHERE id = :todoDetailsId")
    suspend fun updateStatusById(todoDetailsId: UUID, status: Int)

}