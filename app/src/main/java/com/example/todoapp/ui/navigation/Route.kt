package com.example.todoapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TodoList

@Serializable
data class TodoDetail(val id: Int)

@Serializable
object AddTodo