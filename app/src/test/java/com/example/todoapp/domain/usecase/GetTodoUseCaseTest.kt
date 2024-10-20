package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetTodoUseCaseTest {

    private lateinit var getTodoUseCase: GetTodosUseCase
    private lateinit var todoRepository: TodoRepository

    @Before
    fun setup() {
        todoRepository = mock(TodoRepository::class.java)
        getTodoUseCase = GetTodosUseCase(todoRepository)
    }

    @Test
    fun `Should return list of Todos`(): Unit = runBlocking {
        val todoList = listOf(
            Todo(1, "Todo1", null),
            Todo(2, "Todo2", null)
        )
        `when`(todoRepository.getTodos()).thenReturn(todoList)

        val result = getTodoUseCase()

        assertEquals(2, result.size)
        assertEquals("Todo1", result[0].title)
        assertEquals("Todo2", result[1].title)
        verify(todoRepository, times(1)).getTodos()
    }

    @Test
    fun `Should return empty list if no todos are available`(): Unit = runBlocking {

        `when`(todoRepository.getTodos()).thenReturn(emptyList())

        val result = getTodoUseCase()

        assertTrue(result.isEmpty())
        verify(todoRepository, times(1)).getTodos()
    }
}