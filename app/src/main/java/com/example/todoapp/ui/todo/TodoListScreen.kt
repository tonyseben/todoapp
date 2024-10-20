package com.example.todoapp.ui.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.ui.navigation.AddTodo
import com.example.todoapp.ui.navigation.TodoDetail

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoListScreen(
    navController: NavController,
    viewModel: TodoViewModel = hiltViewModel()
) {
    val todoList by viewModel.todoList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TextField(
                value = "",
                onValueChange = {
                    viewModel.onSearchQueryChanged(it)
                },
                label = { Text("Search") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AddTodo)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add TODO")
            }
        }
    ) {
        if (todoList.isEmpty()) {
            Text(
                text = "Press the + button to add a TODO item",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn {
                items(todoList) { todo ->
                    Text(
                        text = "test title",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(TodoDetail(todo.id))
                            }
                    )
                }
            }
        }
    }
}