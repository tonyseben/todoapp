package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapp.ui.todo.AddTodoScreen
import com.example.todoapp.ui.todo.TodoListScreen
import com.example.todoapp.ui.todo.TodoViewModel

@Composable
fun ToDoNavGraph(navController: NavHostController) {
    val todoViewModel: TodoViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = TodoList) {

        composable<TodoList> {
            TodoListScreen(navController = navController, viewModel = todoViewModel)
        }

        composable<AddTodo> {
            AddTodoScreen(navController = navController, viewModel = todoViewModel)
        }
    }
}

