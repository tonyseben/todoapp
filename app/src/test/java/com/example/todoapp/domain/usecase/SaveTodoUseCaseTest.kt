package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class SaveTodoUseCaseTest {

    private lateinit var saveTodoUseCase: SaveTodoUseCase
    private lateinit var todoRepository: TodoRepository

    @Before
    fun setup() {
        todoRepository = mock(TodoRepository::class.java)
        saveTodoUseCase = SaveTodoUseCase(todoRepository)
    }

    @Test
    fun `invoke should add todo to repository`(): Unit = runBlocking {
        // Given
        val newTodo = Todo(id = 0, title = "New Todo", null)

        // When
        saveTodoUseCase(newTodo)

        // Then
        verify(todoRepository, times(1)).addTodo(newTodo)
    }

    @Test
    fun `invoke should throw exception when title is Error`() = runBlocking {
        // Given
        val errorTodo = Todo(id = 0, title = "Error", null)

        // When & Then
        assertThrows(IllegalArgumentException::class.java) {
            runBlocking { saveTodoUseCase(errorTodo) }
        }

        // Verify no interaction with repository due to exception
        verify(todoRepository, never()).addTodo(errorTodo)
    }
}