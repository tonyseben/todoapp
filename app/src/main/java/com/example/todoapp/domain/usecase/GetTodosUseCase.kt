package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodosUseCase @Inject constructor(private val repository: TodoRepository) {

    suspend operator fun invoke(): List<Todo>{
        return repository.getTodos()
    }
}