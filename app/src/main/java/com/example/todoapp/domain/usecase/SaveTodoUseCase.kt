package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class SaveTodoUseCase @Inject constructor(private val repository: TodoRepository) {

    suspend operator fun invoke(todo: Todo) {
        if(todo.title == "Error"){
            throw IllegalArgumentException("Failed to add TODO")
        }
        repository.addTodo(todo)
    }
}