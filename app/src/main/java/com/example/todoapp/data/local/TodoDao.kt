package com.example.todoapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getTodos(): List<Todo>

    @Insert
    suspend fun insertTodo(todo: Todo)
}