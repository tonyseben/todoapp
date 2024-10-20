package com.example.todoapp.ui.todo

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    navController: NavController,
    viewModel: TodoViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var newTodoTitle by remember { mutableStateOf("") }
    val saveStatus by viewModel.saveStatus.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Enter new item") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = newTodoTitle,
                onValueChange = { newTodoTitle = it },
                label = { Text("Enter todo") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (newTodoTitle.isNotBlank()) {
                        coroutineScope.launch {
                            viewModel.onAddTodoClick(title = newTodoTitle)
                        }
                    }
                },
                enabled = saveStatus !is SaveStatus.InProgress
            ) {
                if (saveStatus is SaveStatus.InProgress) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                } else {
                    Text("Add TODO")
                }
            }
        }

        when (saveStatus) {
            is SaveStatus.Success -> {
                LaunchedEffect(Unit) {
                    navController.popBackStack()
                    viewModel.clearSaveStatus()
                }
            }

            is SaveStatus.Failed -> {
                LaunchedEffect(saveStatus) {
                    Toast.makeText(
                        context,
                        (saveStatus as SaveStatus.Failed).errorMessage,
                        Toast.LENGTH_LONG
                    ).show()
                    viewModel.clearSaveStatus()
                }
            }

            else -> {}
        }
    }
}