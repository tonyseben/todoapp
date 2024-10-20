package com.example.todoapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object TodoList

@Serializable
data class TodoDetail(val id: String)

@Serializable
object AddTodo