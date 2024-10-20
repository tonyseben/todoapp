package com.example.todoapp.ui.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.usecase.GetTodosUseCase
import com.example.todoapp.domain.usecase.SaveTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
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

    private val _saveStatus = MutableStateFlow<SaveStatus?>(null)
    val saveStatus: StateFlow<SaveStatus?> = _saveStatus

    init {
        observeSearchQuery()
        getTodos()
    }

    private fun getTodos() {
        viewModelScope.launch {
            _todoList.value = getTodosUseCase()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery
                .debounce(2000)
                .collectLatest { query ->
                    _todoList.value = if (query.isEmpty()) {
                        getTodosUseCase()
                    } else {
                        getTodosUseCase().filter { it.title.contains(query, true) }
                    }
                }
        }
    }

    fun onAddTodoClick(title: String, description: String? = null) {
        viewModelScope.launch {
            _saveStatus.value = SaveStatus.InProgress
            try {
                val todo = Todo(title = title, description = description)
                addTodoUseCase(todo)
                getTodos()
                _searchQuery.value = ""
                _saveStatus.value = SaveStatus.Success
            } catch (e: Exception) {
                _saveStatus.value = SaveStatus.Failed("Error: Something went wrong.")
            }
        }
    }

    fun clearSaveStatus() {
        _saveStatus.value = null
    }
}

sealed class SaveStatus {
    data object InProgress : SaveStatus()
    data object Success : SaveStatus()
    data class Failed(val errorMessage: String) : SaveStatus()
}