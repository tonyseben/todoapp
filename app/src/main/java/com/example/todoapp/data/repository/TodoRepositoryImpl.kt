package com.example.todoapp.data.repository

import com.example.todoapp.data.local.TodoDao
import com.example.todoapp.data.mapper.toDomainModel
import com.example.todoapp.data.mapper.toEntityModel
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {

    override suspend fun getTodos(): List<Todo> {
        return todoDao.getTodos().map { it.toDomainModel() }
    }

    override suspend fun addTodo(todo: Todo) {
        todoDao.insertTodo(todo.toEntityModel())
    }
}