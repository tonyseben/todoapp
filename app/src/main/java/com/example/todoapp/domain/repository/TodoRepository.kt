package com.example.todoapp.domain.repository

import com.example.todoapp.domain.model.Todo


interface TodoRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun addTodo(todo: Todo)
}