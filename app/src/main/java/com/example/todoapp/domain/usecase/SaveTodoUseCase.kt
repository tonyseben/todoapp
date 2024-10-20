package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.delay
import java.util.Locale
import javax.inject.Inject

class SaveTodoUseCase @Inject constructor(private val repository: TodoRepository) {

    suspend operator fun invoke(todo: Todo) {
        if(todo.title.trim().lowercase(Locale.getDefault()) == "error"){
            throw IllegalArgumentException("Failed to add TODO")
        }
        delay(3000)
        repository.addTodo(todo)
    }
}