package com.example.todoapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object TodoList

@Serializable
data class TodoDetail(val id: String)

@Serializable
object AddTodo


@Composable
fun ToDoNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = TodoList){

        composable<TodoList> {
            TodoListScreen()
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

