package com.example.todoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.data.local.entity.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getTodos(): List<TodoEntity>

    @Insert
    suspend fun insertTodo(todo: TodoEntity)
}