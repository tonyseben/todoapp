package com.example.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.usecase.GetTodosUseCase
import com.example.todoapp.domain.usecase.SaveTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: SaveTodoUseCase
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList: StateFlow<List<Todo>> = _todoList

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            _todoList.value = getTodosUseCase()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            _todoList.value = getTodosUseCase().filter { it.title.contains(query, true) }
        }
    }

    fun onAddTodoClick(title: String, description: String? = null) {
        viewModelScope.launch {
            val todo = Todo(title = title, description = description)
            addTodoUseCase(todo)

            _searchQuery.value = ""
            getTodos()
        }
    }
}