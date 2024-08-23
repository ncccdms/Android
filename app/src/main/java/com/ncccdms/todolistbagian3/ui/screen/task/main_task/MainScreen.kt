package com.ncccdms.todolistbagian3.ui.screen.task.main_task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ncccdms.todolistbagian3.core.Utils.Companion.sortTasksByDeadline
import com.ncccdms.todolistbagian3.domain.model.Response
import com.ncccdms.todolistbagian3.domain.repository.Tasks
import com.ncccdms.todolistbagian3.ui.screen.task.list_task.ListViewModel
import com.ncccdms.todolistbagian3.ui.screen.task.main_task.components.NearlyDeadlineSection
import com.ncccdms.todolistbagian3.ui.screen.task.main_task.components.NextDeadlineSection

@Composable
fun MainScreen(
    navigateToDetail: (String) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    // Collect tasks from the ViewModel
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

    // Sort tasks by deadline
    val sortedTasks = when (tasksResponse) {
        is Response.Success -> sortTasksByDeadline((tasksResponse as Response.Success<Tasks>).data)
        else -> emptyList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
    ) {
        // Display the tasks in different sections
        NearlyDeadlineSection(sortedTasks.take(1), navigateToDetail)
        NextDeadlineSection(sortedTasks.drop(1).take(3), navigateToDetail)
    }
}


