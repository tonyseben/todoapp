package com.example.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.todoapp.ui.todo.AddTodoScreen
import com.example.todoapp.ui.todo.TodoDetailScreen
import com.example.todoapp.ui.todo.TodoListScreen

@Composable
fun ToDoNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = TodoList){

        composable<TodoList> {
            TodoListScreen(navController)
        }

        composable<TodoDetail> { entry ->
            val detail: TodoDetail = entry.toRoute()
            TodoDetailScreen(detail.id)
        }

        composable<AddTodo> {
            AddTodoScreen()
        }
    }
}

