package com.ncccdms.todolistbagian3.ui.screen.task.detail_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.domain.model.Response
import com.ncccdms.todolistbagian3.domain.repository.Tasks
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.ListViewModel

@Composable
fun DetailScreen(
    listId: String,
    navigateBack: () -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    // Collect the task response from the ViewModel
    val tasksResponse by viewModel.tasksResponse.collectAsState()

    // Handle loading state
    if (tasksResponse is Response.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    // Find the task with the given ID
    val task = when (tasksResponse) {
        is Response.Success -> (tasksResponse as Response.Success<Tasks>).data.find { it.id == listId }
        is Response.Failure -> {
            // Optionally handle the failure state, e.g., show a toast or log the error
            null
        }
        is Response.Loading -> null // Already handled by the loading state check
    }

    // Display the task details or an error message
    if (task != null) {
        DetailContent(task = task)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Task not found", color = Color.Red, fontSize = 20.sp)
        }
    }
}
